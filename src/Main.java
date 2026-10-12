import java.util.Scanner; 
public class Main{
    public static void main (String[]args){
     Scanner scan= new Scanner (System.in); 
    while(true){
        System.out.print("1. Ajouter une tâche \n2. Afficher les tâches\n3. Terminer une tâche \n4. Modifier la priorité \n5. Quitter\nChoix: ");
       String texte= scan.nextLine();
        int  choix ;
       try{
        choix= Integer.parseInt(texte);

       } catch(NumberFormatException e){
         System.out.println("Veuillez choisir un numero de la liste merci.");


        continue;
       }
       if (choix==1){ 
        System.out.println("en cours de conception");

       }
       else if(choix==2){
        System.out.println("en cours de conception");
       }
       else if(choix==3){
         System.out.println("en cours de conception");

       }
       else if(choix==4){
         System.out.println("en cours de conception");

       }
       else if(choix==5){
        System.out.println("Au revoir !");
        break;
       }
       else {
        System.out.println("Veuillez choisir un numero de la liste merci.");
       }
    }
    scan.close();
        
    }
}