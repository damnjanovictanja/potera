package potera


import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.layout.HBox
import scalafx.scene.Scene
import scalafx.scene.layout.VBox
import scalafx.scene.control.Label
import scalafx.scene.control.Button
import scalafx.geometry.Insets
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.scene.paint._
import scalafx.event.ActionEvent
import scalafx.scene.effect.DropShadow
import scalafx.geometry.Pos
import scalafx.scene.control.TextField
import scalafx.scene.control.Alert
import scalafx.scene.paint.Color._
import scalafx.scene.control.Alert.AlertType
import scala.io.Source
import scalafx.animation.Timeline
import scalafx.animation.KeyFrame
import scalafx.scene.control.ProgressBar
import scalafx.beans.property.DoubleProperty
import scalafx.util.Duration
import javafx.beans.binding.Bindings
import java.util.concurrent.Callable
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.input.ScrollEvent
import scala.concurrent.Await
import scalafx.scene.media.Media
import java.io.File
import scalafx.scene.media.MediaPlayer



object Dvaprim {
  

  Potera.mediaPlayerDvaPrim.setOnEndOfMedia(new Runnable(){
        def run:Unit={
          Potera.mediaPlayerDvaPrim.seek(Duration.ZERO)
        }
      })
      
       val r = scala.util.Random 
       var br1 = (r.nextInt(4))
       br1 = br1 + 2
       var br2 = (r.nextInt(5))
       br2 = br2 + 2
       Potera.takmicar.index = 5
    var dvaprim = new Scene(1000,600){
       
       Potera.mediaPlayerDvaPrim.play()
       val vbox = new VBox{
         
         spacing = 20
    	   padding = Insets(30,500,100,100)
         
         val vecaCifra = new Label{
           text = "Ukoliko zelite da pridjete korak blize tragacu, ponudjena svota novca je:"
           style = """-fx-font: normal italic 15pt sans-serif;
                      -fx-text-fill: #fff"""
         }
         val vecaDugme = new Button{
           prefHeight = 50
           prefWidth = 500
           
           text = (Potera.takmicar.poeni*br2).toString()
           style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);
                           -fx-background-radius: 5;
                           -fx-background-insets: 0,1,2,3,0;
                           -fx-text-fill: #3f4042;
                           -fx-font-weight: bold;
                           -fx-font-size: 15px;
                           -fx-padding: 5 10 5 10;"""
                      
         }
         val istaCifra = new Label{
           text = "Ukoliko zelite da zadrzite svoju svotu novca:"
           style = """-fx-font: normal italic 15pt sans-serif;
                      -fx-text-fill: #fff"""         
           }
         val istaDugme = new Button{
           prefHeight = 50
           prefWidth = 500
           text = Potera.takmicar.poeni.toString()
           style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);
                           -fx-background-radius: 5;
                           -fx-background-insets: 0,1,2,3,0;
                           -fx-text-fill: #3f4042;
                           -fx-font-weight: bold;
                           -fx-font-size: 15px;
                           -fx-padding: 5 10 5 10;"""
         }
         val manjaCifra =new Label{
           text = "Ukoliko zelite da odete korak dalje tragacu, ponudjena svota novca je: "
           style = """-fx-font: normal italic 15pt sans-serif;
                      -fx-text-fill: #fff"""  
         }
         val manjaDugme = new Button{
           prefHeight = 50
           prefWidth = 500
           text = (Potera.takmicar.poeni/br2).toString()
           style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);
                           -fx-background-radius: 5;
                           -fx-background-insets: 0,1,2,3,0;
                           -fx-text-fill: #3f4042;
                           -fx-font-weight: bold;
                           -fx-font-size: 15px;
                           -fx-padding: 5 10 5 10;"""
         }
         children.addAll(vecaCifra, vecaDugme, istaCifra, istaDugme, manjaCifra,manjaDugme)
     
         istaDugme.onAction = (event: ActionEvent) =>{
           Potera.takmicar.index = 5
           if (Potera.kraj_dvaPrim)
             Potera.stage.setScene(Treca.treca)
             Potera.kraj_dvaPrim = true
             Treca
           //timeline1.play()
    }
       manjaDugme.onAction = (event: ActionEvent) =>{
           Potera.takmicar.index = 4
           Potera.takmicar.poeni = Potera.takmicar.poeni/br2
           Potera.stage.setScene(Treca.treca)
           Potera.kraj_dvaPrim = true
           Treca
           //timeline1.play()
    }
       vecaDugme.onAction = (event: ActionEvent) =>{
         Potera.takmicar.poeni=Potera.takmicar.poeni*br1
           Potera.takmicar.index = 6
           Potera.stage.setScene(Treca.treca)
           Potera.kraj_dvaPrim = true
           Treca
           //timeline1.play()
    }
       }
     var canvas = new Canvas (1000,600)
     var gc = canvas.graphicsContext2D;

     gc.drawImage(new Image(Potera.slika_url), 0, 0); 
     content= List(canvas, vbox)
   
}
    
    if (Potera.kraj_druge) {
     Potera.mediaPlayerDruga.stop()
     Potera.stage.setScene(dvaprim) 
    }
}