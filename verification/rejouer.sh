#!/usr/bin/env bash
#
# Rejoue les scénarios de verification/scenarios/ et compare chaque sortie
# avec sa référence dans verification/references/.
#
#   verification/rejouer.sh                        compare avec les références
#   verification/rejouer.sh --references v1.0.0   régénère les références à partir de l'étiquette v1.0.0
#
# Chaque scénario part d'un dossier vide. Si verification/donnees/<scénario>.csv
# existe, il y est copié sous le nom taches.csv avant le lancement. Après
# l'exécution, le contenu de taches.csv est ajouté à la sortie pour vérifier
# la sauvegarde.

set -u

RACINE="$(cd "$(dirname "$0")/.." && pwd)"
VERIF="$RACINE/verification"
TRAVAIL="$(mktemp -d)"
trap 'rm -rf "$TRAVAIL"' EXIT

MODE="comparaison"
SOURCES="$RACINE/src"

if [ "${1:-}" = "--references" ]; then
    if [ -z "${2:-}" ]; then
        echo "Indiquez l'étiquette, par exemple : --references v1.0.0"
        exit 1
    fi
    VERSION="$2"
    MODE="references"
    mkdir -p "$TRAVAIL/version"
    (cd "$RACINE" && git archive "$VERSION" src) | tar -x -C "$TRAVAIL/version" || exit 1
    SOURCES="$TRAVAIL/version/src"
fi

mkdir -p "$TRAVAIL/classes"
find "$SOURCES" -name '*.java' > "$TRAVAIL/sources.txt"
if ! javac -encoding UTF-8 -d "$TRAVAIL/classes" @"$TRAVAIL/sources.txt"; then
    echo "Échec de la compilation."
    exit 1
fi

executer() {
    local scenario="$1" sortie="$2"
    local nom dossier
    nom="$(basename "$scenario" .txt)"
    dossier="$TRAVAIL/execution/$nom"
    mkdir -p "$dossier"
    if [ -f "$VERIF/donnees/$nom.csv" ]; then
        cp "$VERIF/donnees/$nom.csv" "$dossier/taches.csv"
    fi
    (cd "$dossier" && timeout 10 java -Dstdout.encoding=UTF-8 -cp "$TRAVAIL/classes" Main < "$scenario") > "$sortie"
    if [ -f "$dossier/taches.csv" ]; then
        echo "--- taches.csv ---" >> "$sortie"
        cat "$dossier/taches.csv" >> "$sortie"
    fi
}

if [ "$MODE" = "references" ]; then
    mkdir -p "$VERIF/references"
    for scenario in "$VERIF"/scenarios/*.txt; do
        nom="$(basename "$scenario" .txt)"
        executer "$scenario" "$VERIF/references/$nom.txt"
        echo "Référence produite : $nom"
    done
    exit 0
fi

total=0
echecs=0
for scenario in "$VERIF"/scenarios/*.txt; do
    nom="$(basename "$scenario" .txt)"
    total=$((total + 1))
    executer "$scenario" "$TRAVAIL/sortie.txt"
    if diff --strip-trailing-cr "$VERIF/references/$nom.txt" "$TRAVAIL/sortie.txt" > "$TRAVAIL/diff.txt"; then
        echo "OK      $nom"
    else
        echo "ÉCHEC   $nom"
        cat "$TRAVAIL/diff.txt"
        echecs=$((echecs + 1))
    fi
done

echo
echo "$total scénarios rejoués, $echecs avec une différence."
[ "$echecs" -eq 0 ]
