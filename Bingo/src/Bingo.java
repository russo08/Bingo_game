// Eduardo Maya

import java.util.Random;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;


public class Bingo  extends Application
{
	static  Label lbs1[];
	static  Label lbs2[];
	int random[];
	TextField getNumberTextField = new TextField("");
	TextField getNumberTextField2 = new TextField("");
	Label textLabel = new Label("");
	int p1_array[];
	int p2_array[];
	int b1_count = 0;
	int b2_count = 0;
	int rand_count = 0;
	
	
	   public boolean Repeat(Label [] nums, int size, int num) // ChecK for repetition of user selected numbers  
	   {
		   int label_num = 0;
		   boolean isRepeat = false;
		   for (int i = 0; i <= size; i++)
		   {
			   label_num = Integer.parseInt(nums[i].getText());
			   if(label_num == num)
			   {
				   isRepeat = true;
			   }
		   }

		   return isRepeat;
	   }
	
	   static void randomize( int arr[], int size) // Randomize the array of numbers between 10-40  
	    { 
		   int temp_index = 0;
		   int temp_val = 0;
		   Random rand = new Random();
		   
		   for (int i = 0; i < size; i++)
		   {
			   temp_index = rand.nextInt(size);
			   temp_val = arr[temp_index];
			   arr[temp_index] = arr[i];
			   arr[i] = temp_val;
			   
		   }
	    } 
	   // Check winner return both=0, user1=1, user2=2, or no more random number =-1
	   public int checkWinner() 
	   {
		   int winner = -1;	// default value
		   if (checkArray(p1_array) && checkArray(p2_array)) // check to see if both player arrays have winner
		   {
			   winner = 0;
		   }
		   else if (checkArray(p1_array)) // check to see if only player 1 array has winning numbers
		   {
			   winner = 1;
		   }
		   else if (checkArray(p2_array)) // check to see if only player2 array has winning numbers
		   {
			   winner = 2;
		   }
		   return winner;
	   }
	   public int[] convert_array(Label[] nums) // Converts user arrays from String to Integer array
	   {	int count = 0;
	   		int[] array = new int[25];
		   for(int i = 0; i < 5; i++)
		   	{
			   for (int j = 0; j < 5; j++)
			   {
				   array[count] = Integer.parseInt(nums[count].getText());
				   count++;
			   }
		   	}
		   return array;
	   }
	   
	   public void notValidNum() // Displays warning for users if number is not between 10-40
	   {
		   Alert alert = new Alert(Alert.AlertType.INFORMATION);
		   alert.initStyle(StageStyle.UTILITY);
		   alert.setTitle("Warning");
		   alert.setHeaderText(null);
		   alert.setContentText("Entry must be a number between 10 and 40");
		   alert.showAndWait();
	   }
	   
	   public void inputExceed() // Displays warning telling users that they can only enter 25 numbers
	   {
		   Alert alert = new Alert(Alert.AlertType.INFORMATION);
		   alert.initStyle(StageStyle.UTILITY);
		   alert.setTitle("Warning");
		   alert.setHeaderText(null);
		   alert.setContentText("You cannot enter more than 25 numbers");
		   alert.showAndWait();
	   }
	   
	   public void numRepeat() // Displays warning telling users they cannot have repeated numbers
	   {
		   Alert alert = new Alert(Alert.AlertType.INFORMATION);
		   alert.initStyle(StageStyle.UTILITY);
		   alert.setTitle("Warning");
		   alert.setHeaderText(null);
		   alert.setContentText("You cannot enter repeated numbers");
		   alert.showAndWait();
	   }
	   
	   public void incomplete() // Displays warning if either player cards are not complete
	   {
		   Alert alert = new Alert(Alert.AlertType.INFORMATION);
		   alert.initStyle(StageStyle.UTILITY);
		   alert.setTitle("Warning");
		   alert.setHeaderText(null);
		   alert.setContentText("Players cards have not been filled out completely");
		   alert.showAndWait();
	   }
	   // Creates pop up window displaying the winner
	   public void winnerDisplay (int win_num)
	   {
		   if(win_num == 0)	// Both players win
		   	{
			   Alert alert = new Alert(Alert.AlertType.INFORMATION);
			   alert.initStyle(StageStyle.UTILITY);
			   alert.setTitle("Warning");
			   alert.setHeaderText(null);
			   alert.setContentText("Congratulations! Both players are the winner.");
			   alert.showAndWait();
			   System.exit(0);
		   	}
		   else if (win_num == 1)	// Player 1 is the winner
		   	{
			   Alert alert = new Alert(Alert.AlertType.INFORMATION);
			   alert.initStyle(StageStyle.UTILITY);
			   alert.setTitle("Warning");
			   alert.setHeaderText(null);
			   alert.setContentText("Congratulations! Player 1 is the winner.");
			   alert.showAndWait();
			   System.exit(0);
		   	}
		   else if(win_num == 2) // Player 2 is the winner
		    {
			   Alert alert = new Alert(Alert.AlertType.INFORMATION);
			   alert.initStyle(StageStyle.UTILITY);
			   alert.setTitle("Warning");
			   alert.setHeaderText(null);
			   alert.setContentText("Congratulations! Player 2 is the winner.");
			   alert.showAndWait();
			   System.exit(0);
		    }
		   else if (win_num == -1 && rand_count == random.length) // No winner for this round
		   {
			   Alert alert = new Alert(Alert.AlertType.INFORMATION);
			   alert.initStyle(StageStyle.UTILITY);
			   alert.setTitle("Warning");
			   alert.setHeaderText(null);
			   alert.setContentText("There is no winner this round.");
			   alert.showAndWait();
			   System.exit(0);
		   }
		   else {
			   // Do nothing
		   }
			
	   }
	   // Finds the index of the Player arrays that matches the random number rolled
	   public int findIndex (int arr [], int r_num)
	   {
		   int index = -1;
		   int i = 0;
		   while (i < 25)
		   {
			   if(arr[i] == r_num)
			   {
				   index = i;
			   }
			   i++;
		   }
		   return index;
	   }
	   
	   public boolean checkArray(int arr[]) 
	   {
		    if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0 && arr[3] == 0 && arr[4] == 0)	//if first row is all 0s winner
		    {
		    	return true;
		    }
			if (arr[5] == 0 && arr[6] == 0 && arr[7] == 0 && arr[8] == 0 && arr[9] == 0)	    //if second row is all 0s winner
				return true;
			if (arr[10] == 0 && arr[11] == 0 && arr[12] == 0 && arr[13] == 0 && arr[14] == 0)	//if third row is all 0s winner
				return true;
			if (arr[15] == 0 && arr[16] == 0 && arr[17] == 0 && arr[18] == 0 && arr[19] == 0)	//if forth row is all 0s winner
				return true;
			if (arr[20] == 0 && arr[21] == 0 && arr[22] == 0 && arr[23] == 0 && arr[24] == 0)	//if fifth row is all 0s winner
				return true;
			if (arr[0] == 0 && arr[6] == 0 && arr[12] == 0 && arr[18] == 0 && arr[24] == 0)		//if diagonal left top to bottom right is all 0s winner
				return true;
			if (arr[4] == 0 && arr[8] == 0 && arr[12] == 0 && arr[16] == 0 && arr[20] == 0)		//if diagonal left bottom to top right is 0s winner
				return true;
			if (arr[0] == 0 && arr[4] == 0 && arr[20] == 0 && arr[24] == 0)		                //if all four corner numbers are 0s winner
				return true;
			if (arr[0] == 0 && arr[5] == 0 && arr[10] == 0 && arr[15] == 0 && arr[20] == 0)		//if first column is all 0s winner
				return true;
			if (arr[1] == 0 && arr[6] == 0 && arr[11] == 0 && arr[16] == 0 && arr[21] == 0)		//if second column is all 0s winner
				return true;
			if (arr[2] == 0 && arr[7] == 0 && arr[12] == 0 && arr[17] == 0 && arr[22] == 0)		//if third column is all 0s winner
				return true;
			if (arr[3] == 0 && arr[8] == 0 && arr[13] == 0 && arr[18] == 0 && arr[23] == 0)		//if forth column is all 0s winner
				return true;
			if (arr[4] == 0 && arr[9] == 0 && arr[14] == 0 && arr[19] == 0 && arr[24] == 0)		//if fifth column is all 0s winner
				return true;
		   return false;
	   }
	   @Override
	   public void start(Stage primaryStage)
	   {
		   	  int count=0;
		      Label lbsup[];
		      lbsup  = new Label[10];
		      Label lbsdown[];
		      lbsdown  = new Label[10];
		      GridPane gridpane = new GridPane();
		      Button myButton = new Button("  Random #");
		      myButton.setOnAction(new ButtonClickHandlerRamdom());
		      Button Buttonfill1 = new Button("Fill Player 1 card");
		      Buttonfill1.setOnAction(new ButtonClickHandlerfill1());
		      //Buttonfill1.setOnAction(new Button1Reset());
		      Button Buttonfill2 = new Button("Fill player 2 card");
		      Buttonfill2.setOnAction(new ButtonClickHandlerfill2());
		      Button exitButton = new Button("        Exit     ");
		      exitButton.setOnAction(new ExitButtonListener());
		      textLabel.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      random = new int[40];
		      for (int i = 0; i < 40; i++) 
		      {
		    	  random[i]=i+10;
		      }
		      randomize(random,40);
		     
		      for (int i = 0; i < 10; i++) 
		      {
		    	  lbsup[i]=new Label("---");
		    	  lbsup[i].setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
		    	  lbsdown[i]=new Label(" | ");
		    	  lbsdown[i].setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
		      }  
		      Label label1 = new Label(" B ");
		      label1.setStyle("-fx-font-size: 20px;-fx-border-color: black; -fx-font-weight: bold;");
		      Label label2 = new Label(" I  ");
		      label2.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      Label label3 = new Label(" N ");
		      label3.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      Label label4 = new Label(" G ");
		      label4.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      Label label5 = new Label(" O");
		      label5.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      for (int i = 0; i < 5; i++) 
		      gridpane.add(lbsup[i], i+1,1);
		      
		      gridpane.add(label1, 1,0);
		      gridpane.add(label2, 2,0);
		      gridpane.add(label3, 3,0);
		      gridpane.add(label4, 4,0);
		      gridpane.add(label5, 5,0);
		      lbs1 = new Label[25];
		      count=0;
		      for (int i = 0; i < 5; i++) 
		      {
		    	  for (int j = 0; j < 5; j++) 
			      {
		    		lbs1[count]=new Label("00");
		            lbs1[count].setStyle("-fx-font-size: 20px;-fx-border-color: black;");
		            gridpane.add(lbs1[count], j+1,i+2);
		            count++;
			      }
		    	  gridpane.add(lbsdown[i], 7,i+2);
		      }
		      Label label11 = new Label(" B ");
		      label11.setStyle("-fx-font-size: 20px;-fx-border-color: black; -fx-font-weight: bold;");
		      Label label21 = new Label(" I  ");
		      label21.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      Label label31 = new Label(" N ");
		      label31.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      Label label41 = new Label(" G ");
		      label41.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      Label label51 = new Label(" O");
		      label51.setStyle("-fx-font-size: 20px; -fx-border-color: black;-fx-font-weight: bold;");
		      gridpane.add(label11, 9,0);
		      gridpane.add(label21, 10,0);
		      gridpane.add(label31, 11,0);
		      gridpane.add(label41, 12,0);
		      gridpane.add(label51, 13,0);
		      for (int i = 5; i < 10; i++) 
		      gridpane.add(lbsup[i], i+4,1);
		      lbs2 = new Label[25];
		      count=0;
		      for (int i = 0; i < 5; i++) 
		      {
		    	  for (int j = 0; j < 5; j++) 
			      {
		    		  lbs2[count]=new Label("00");
			          lbs2[count].setStyle("-fx-font-size: 20px;-fx-border-color: black;");
		            gridpane.add(lbs2[count], j+9,i+2);
		            count++;
			      }
		      }
		      
		      getNumberTextField.setPrefWidth(10);
		      gridpane.add(getNumberTextField,20,1);
		      gridpane.add(Buttonfill1,20,2);
		      gridpane.add(Buttonfill2,20,3);
		      textLabel.setPrefWidth(80);
		      textLabel.setAlignment(Pos.CENTER);
		      gridpane.add(textLabel,20,4);
		      gridpane.add(myButton,20,5);
		      gridpane.add(exitButton,20,6);
		      gridpane.setHgap(10);
		      gridpane.setVgap(10);
		      Scene scene = new Scene(gridpane, 700, 300);
		      scene.setFill(Color.BEIGE);
		      primaryStage.setTitle("Eduardo Maya's BINGO");
		      primaryStage.setScene(scene);
		      primaryStage.show();
		      
		      // Pop up message asking player 1 to enter 24 numbers
		      Alert alert = new Alert(Alert.AlertType.INFORMATION);
			  alert.initStyle(StageStyle.UTILITY);
			  alert.setTitle("Attention");
			  alert.setHeaderText(null);
			  alert.setContentText("Player 1, please select your 24 numbers");
			  alert.showAndWait();
	   }
	   class ButtonClickHandlerRamdom implements EventHandler<ActionEvent>
	      {
	         @Override
	         public void handle(ActionEvent event) // Dictates what happens when the random button is pressed
	         {
	        	 if (b1_count < 25 || b2_count < 25) // Cannot press random button if both player arrays are not filled
	        	 {
	        		 incomplete();
	        	 }
	        	 
	        	 else
	        	 {
	        		 int rand_num = random[rand_count]; // get random number from random array
	        		 rand_count++;
	        		 textLabel.setText(String.valueOf(rand_num)); // prints random number in text label
	        		 int indexP1 = findIndex(p1_array, rand_num); // checks player 1 array for random number
	        		 int indexP2 = findIndex(p2_array, rand_num); // checks player 2 array for random number
	        		 
	        		 if (indexP1 != -1) // set location of random number in Player 1 array to zero
	        		 {
	        			 p1_array[indexP1] = 0;
	        			 lbs1[indexP1].setText("00");
	        		 }
	        		 if(indexP2 != -1) // set location of random number in Player 2 array to zero
	        		 {
	        			 p2_array[indexP2] = 0;
	        			 lbs2[indexP2].setText("00");
	        		 }
	        		 if(rand_count >= 5)
	        		 {
	        			 winnerDisplay(checkWinner());
	        		 }
	        	 }
	      	 }
	       }
	   // Fill the first array of Labels
	   class ButtonClickHandlerfill1 implements EventHandler<ActionEvent>
	      {
		   
	         @Override
	         public void handle(ActionEvent event) // Handles actions for the player 1 fill button
	         {
	        	 if(b1_count == 25) // Cannot enter more than 24 numbers
	        	 	{
	        		 inputExceed();
	        	 	}
	        	 else 
	        	 	{
	        		 try { // Check to make sure that the player input is a number by converting input to integer
	        			 Integer.parseInt(getNumberTextField.getText()); 
	        		 }
	        		 catch(NumberFormatException e) {
	        			 notValidNum();
	        		 }
	        		 if(Integer.parseInt(getNumberTextField.getText()) < 10 || Integer.parseInt(getNumberTextField.getText()) > 40) // Check to see if number is between 10 and 40
	        			{
	        			 	notValidNum();
	        			}
	        		 else if (Repeat(lbs1, b1_count, Integer.parseInt(getNumberTextField.getText()))) // Checks to make sure number input is not repeated in player 1 array
	        		 	{
	        			 	numRepeat();
	        		 	}
	        		 else 
	        		 	{	if(b1_count == 12) // skip the 12th index, "Free Space"
	        		 		{
	        		 			b1_count++;
	        		 		}
	        			 	lbs1[b1_count].setText(getNumberTextField.getText()); // Set the number field text to user input number
	        			 	b1_count++;
	        			 	getNumberTextField.setText("");
	        			 	if(b1_count == 25) // Display player 2 prompt to input their numbers
	        			 	{
	        			 		p1_array = convert_array(lbs1); // convert string from string to integer
	        			 		
	        			 		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        					alert.initStyle(StageStyle.UTILITY);
	        					alert.setTitle("Attention");
	        					alert.setHeaderText(null);
	        					alert.setContentText("Player 2, please select your 24 numbers");
	        					alert.showAndWait();
	        			 	}
	        		 	}
	        	 	}
	         }
	         
	      }

	// Fill the second array of Labels
	   class ButtonClickHandlerfill2 implements EventHandler<ActionEvent>
	      {
	         @Override
	         public void handle(ActionEvent event) // Handles event for player 2 fill button
	         {
	        	 if(b2_count == 25) // Cannot enter more than 24 numbers
	        	 	{
	        		 inputExceed();
	        	 	}
	        	 else 
	        	 	{
	        		 try { // Check to make sure that the player input is a number by converting input to an integer
	        			  Integer.parseInt(getNumberTextField.getText()); 
	        		 }
	        		 catch(NumberFormatException e) {
	        			 notValidNum();
	        		 }
	        		
	        		 if(Integer.parseInt(getNumberTextField.getText()) < 10 || Integer.parseInt(getNumberTextField.getText()) > 40) // Check to see if number is between 10 and 40
	        			{
	        			 	notValidNum();
	        			}
	        		 else if (Repeat(lbs2, b2_count, Integer.parseInt(getNumberTextField.getText()))) // Checks to make sure number choice is not repeated in player 2 array
	        		 	{
	        			 	numRepeat();
	        		 	}
	        		 else 
	        		 	{	if(b1_count < 25) // Checks to make sure player 1 has finished filling their card before player 2 can start
	        		 		{
	        		 			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	   			  		alert.initStyle(StageStyle.UTILITY);
	    	   			  		alert.setTitle("Attention");
	    	   			  		alert.setHeaderText(null);
	    	   			  		alert.setContentText("Player 1, is not finished selecting");
	    	   			  		alert.showAndWait();
	        		 		}
	        		 		else
	        		 		{
	        		 			if(b2_count == 12) // skip the 12th index, "Free Space"
	        		 			{
	        		 				b2_count++;
	        		 			}
	        		 			lbs2[b2_count].setText(getNumberTextField.getText()); // Set the value in player 2 array text field to their chosen number
	        		 			b2_count++;
	        		 			getNumberTextField.setText("");
	        		 			if(b2_count == 25)
	        		 			{
	        		 				p2_array = convert_array(lbs2); // convert array from string to integer
	        		 			}
	        		 		}
	        		 	}
	        	 	}
	        	 }
	         }
	   public class ExitButtonListener implements EventHandler<ActionEvent> 
	   {
		   @Override
		   public void handle(ActionEvent arg0) 
		   {
			 System.exit(0);
		   }
	   }
	   public static void main(String[] args)
	   {
	      // Launch the application.
	      launch(args);
	      
	   }
}