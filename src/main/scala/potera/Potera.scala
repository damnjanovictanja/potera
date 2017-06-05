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
import java.io._
import scalafx.scene.media.MediaPlayer

object Potera extends JFXApp{
  var takmicar = new Takmicar
  takmicar.index=4
  var indextr = 8
  
  
  //Muzika za prvu scenu
  
  var url = getClass().getClassLoader().getResource("muzika/potera_tema.mp3").toString();
 
  val mediaTema = new Media(url)
  val mediaPlayerTema = new MediaPlayer(mediaTema)

  //Muzika za drugu scenu

  var urldruga = getClass().getClassLoader().getResource("muzika/druga.mp3").toString();
 
  val mediaDruga = new Media(urldruga)
  val mediaPlayerDruga = new MediaPlayer(mediaDruga)


  //Muzika za dva prim scenu
  var urldvaprim = getClass().getClassLoader().getResource("muzika/potera_tema.mp3").toString();
 
  val mediaDvaPrim = new Media(urldvaprim)
  val mediaPlayerDvaPrim = new MediaPlayer(mediaDvaPrim)

  //Muzika za trecu scenu

  var urltreca = getClass().getClassLoader().getResource("muzika/zavrsno.mp3").toString();
 
  val mediaTreca = new Media(urltreca)
  val mediaPlayerTreca = new MediaPlayer(mediaTreca)

  //Muzika za finalnu scenu

  var urlfinalna = getClass().getClassLoader().getResource("muzika/zavrsno.mp3").toString();
 
  val mediaFinalna = new Media(urlfinalna)
  val mediaPlayerFinalna = new MediaPlayer(mediaFinalna)  

  //slika
  var slika_url = getClass().getClassLoader().getResource("slike/dubine.jpg").toString();



  // TIMER ZA DRUGU SCENU
  val progress = new ProgressBar {
    minWidth = 50
  }
  val numericProgress = new Label()
  val elapsed = DoubleProperty(0)
  progress.progress <== elapsed / 600
  numericProgress.text <== Bindings.createStringBinding(new Callable[String] { override def call(): String =
    formatElapsed(elapsed())
    }, elapsed)
  numericProgress.style="""-fx-font-size: 15pt;
           -fx-text-fill: #fff;"""
  val timeline = Timeline(KeyFrame(Duration(60), "", (e: ActionEvent) =>
    if (elapsed() < 600) elapsed() = elapsed() + 0.6))
  timeline.setCycleCount(Timeline.INDEFINITE)
  
  def formatElapsed(elapsed: Double): String = {
    val seconds = (elapsed / 10.0).floor
    val dezipart = elapsed % 10
    seconds.toInt + "." + dezipart.toInt + "s"
  }  
  
  //TIMER ZA TRECU SCENU
  var progress1 = new ProgressBar {
    minWidth = 50
  }
  val numericProgress1 = new Label()
  var elapsed1 = DoubleProperty(0)
  progress1.progress <== elapsed1 / 100
  numericProgress1.text <== Bindings.createStringBinding(new Callable[String] { override def call(): String =
    formatElapsed(elapsed1())
    }, elapsed1)
  numericProgress1.style="""-fx-font-size: 15pt;
           -fx-text-fill: #727489;"""
  var timeline1 = Timeline(KeyFrame(Duration(10), "", (e: ActionEvent) =>
    if (elapsed1() < 100) elapsed1() = elapsed1() + 0.1))
  timeline1.setCycleCount(Timeline.INDEFINITE)
  
  
  // UCITAVANJE SETA PITANJA
  val stream : InputStream = getClass.getResourceAsStream("/qf.txt")
  val niz_linija = scala.io.Source.fromInputStream( stream ).getLines.toArray

  val broj_pitanja = (niz_linija.length)/5;  
 // val r = scala.util.Random 
  var array = Array.fill(broj_pitanja+1){0}
    //  var br = (r.nextInt(broj_pitanja+1)) 
   // array(br)=1
   // br=br*5
   
    
    // Dodajemo da je indikatore da je pocela scena ili ne.
    var kraj_prve = false
    var kraj_druge = false
    var kraj_dvaPrim = false
    var kraj_trece = false
    
    
  
  stage = new JFXApp.PrimaryStage {
        
   mediaPlayerTema.setOnEndOfMedia(new Runnable(){
     def run:Unit={
       mediaPlayerTema.seek(Duration.ZERO) 
          }
   })
     
   title.value = "Potera"
   centerOnScreen()
   width = 1000
   height = 600
   resizable = false

  scene = new Scene {
     
    mediaPlayerTema.play()
     var canvas = new Canvas (1000,600)
     var gc = canvas.graphicsContext2D;

     gc.drawImage(new Image(slika_url), 0, 0);
     var v_box = new VBox {
          spacing = 15
          padding = Insets (50,0,100,250)
          val tekst = new Text {
            text = "POTERA"
            alignment = Pos.CENTER
            style = "-fx-font-size: 100pt"
            fill = new LinearGradient (
              endX = 0,
              stops = Stops (Red, Blue))
            effect = new DropShadow {
              color = DodgerBlue
              radius = 25
              spread = 0.25
            }
          }
          val ime_label = new Label("Unesite ime:")
          ime_label.style="""-fx-font-size: 15pt;
                  -fx-text-fill: #fff;"""

          val ime_text = new TextField()
          val buttons = new HBox {
            spacing = 200
            alignment = Pos.CENTER
            val start = new Button {
              text = "START"
              prefWidth=100
              prefHeight=40
              style="""-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
            } 
            val quit_button = new Button {
              text = "KRAJ" 
              prefWidth=100
              prefHeight=40
              style="""-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
            }
            quit_button.onAction =(event:ActionEvent) => {
                  stage.close()
                  
            }
            start.onAction = (event:ActionEvent) => {
              val ime_takmicara = ime_text.getText
              if (ime_takmicara == "") {
                new Alert(AlertType.Warning) {
                  title = "Upozorenje!"
                  headerText = "Niste uneli ime!"
                  contentText = "Da biste zapoceli igru, morate da unesite ime."
                }.showAndWait()

              }
              else {
              takmicar.ime=ime_takmicara
              kraj_prve = true
              Druga
              timeline.play()
              }
            }
            
            children.addAll(start, quit_button)
          }
          children.addAll(tekst, ime_label, ime_text, buttons)
          
      }
      content = List(canvas,v_box)
   }
   
   
   
  } 


}
