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


object Finalna {
  
  

  Potera.mediaPlayerFinalna.setOnEndOfMedia(new Runnable(){
        def run:Unit={
          Potera.mediaPlayerFinalna.seek(Duration.ZERO)
        }
      })
 
  var finalna = new Scene{
    
    Potera.mediaPlayerFinalna.play()
     var canvas = new Canvas (1000,600)
     var gc = canvas.graphicsContext2D;

     gc.drawImage(new Image(Potera.slika_url), 0, 0);
      
          val button2 = new Button{
            prefHeight = 40
            prefWidth = 150
            text = "Izadji"
          }
        
        val pobedio_labela = new Label{
          style = """-fx-font-size: 15pt;
                  -fx-text-fill: #fff;"""
          }
        if (Potera.takmicar.poeni <= 0)
          pobedio_labela.text = "Nazalost " + Potera.takmicar.ime + ", izgubili ste. Vise srece drugi put!"
        else
         pobedio_labela.text = "Cestitam " + Potera.takmicar.ime + ", pobedili ste! Osvojena suma: " + Potera.takmicar.poeni.toString() + "."
 

          button2.onAction = (event: ActionEvent) => {          
            Potera.stage.close()
          }
          
          button2.style ="""-fx-background-color: 
      linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #3f4042;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
       
       var vb = new VBox {
               spacing = 30
        padding = Insets(60, 100, 60, 60)
        alignment = Pos.CENTER
               children.addAll(pobedio_labela,button2)
          }
        
    content = List(canvas,vb)
  
  }
  if(Potera.kraj_trece || Potera.kraj_druge){
    if(Potera.kraj_druge){
      Potera.mediaPlayerDruga.stop()
    }
    if(Potera.kraj_trece){
      Potera.mediaPlayerTreca.stop()
    }
    Potera.stage.setScene(finalna)
  }
}