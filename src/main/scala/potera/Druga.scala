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
import java.io.File
import scalafx.scene.image.Image
import scalafx.scene.media.Media
import scalafx.scene.media.MediaPlayer

object Druga {
  
  Potera.mediaPlayerDruga.setOnEndOfMedia(new Runnable(){
        def run:Unit={
          Potera.mediaPlayerDruga.seek(Duration.ZERO)
        }
      })
 

 // val niz_linija = Source.fromFile("qf.txt").getLines.toArray
  val broj_pitanja = (Potera.niz_linija.length)/5;  
  val r = scala.util.Random 
  var ucitano = 1
  // var array = Array.fill(broj_pitanja+1){0}
      var br = (r.nextInt(broj_pitanja+1)) 
    Potera.array(br)=1
    br=br*5

  
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
  
  var pitanje1 = new Pitanje(Potera.niz_linija(br),Potera.niz_linija(br+1), Potera.niz_linija(br+2), Potera.niz_linija(br+3))
  
  
  
  
  
  var druga = new Scene{    
    
    
    Potera.mediaPlayerDruga.play()
    val zarada = new Label(Potera.takmicar.poeni.toString()){
      alignment = Pos.BASELINE_LEFT
      alignmentInParent = Pos.BASELINE_LEFT
      style = """-fx-font-size: 15pt;
                  -fx-text-fill: #fff;"""
    }
    
    val pitanje = new Label {
      text = pitanje1.p
      alignment=Pos.CENTER
      style = """-fx-font-size: 15pt;
                  -fx-text-fill: #fff;"""
    }
    val dugme1 = new Button {
      style="""-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
      prefWidth = 500
      prefHeight = 50
    }  
    val dugme2 = new Button {
      style="""-fx-background-color:  linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
      prefWidth = 500
      prefHeight = 50
    } 
    var br1 = r.nextInt(2)
      if (br1==0) {
        dugme1.text = pitanje1.to
        dugme2.text = pitanje1.po1
      }
      else {
        dugme1.text= pitanje1.po2  
        dugme2.text = pitanje1.to 
      }
      
         
    var dalje = new Button ("DALJE"){
         style="""-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         prefWidth = 500
         prefHeight = 50
       }
      var sadrzaj = new VBox { 
        minWidth=1000  
        spacing = 50
        padding = Insets(50)
        alignment = Pos.CENTER
        alignmentInParent = Pos.CENTER
        val hBox = new VBox { 
          spacing = 10
          alignment = Pos.CENTER
          alignmentInParent = Pos.CENTER
          children.addAll(dugme1, dugme2, dalje )
        }
     
       val hBox1 = new HBox { 
       spacing = 200
       alignment = Pos.CENTER
       alignmentInParent = Pos.CENTER
        
       val vreme = new HBox(10) { 
         var vreme_label = new Label {
           text = "Vreme: "
           style =  """-fx-font-size: 15pt;
           -fx-text-fill: #fff;"""
         }
         children.addAll(vreme_label, progress, numericProgress)}
      children.addAll(zarada, vreme )
      }
      
  children.addAll(
    pitanje,
    hBox,
    hBox1
  )
    }
     var canvas = new Canvas (1000,600)
     var gc = canvas.graphicsContext2D;

     gc.drawImage(new Image(Potera.slika_url), 0, 0);
     content= List(canvas, sadrzaj)
     
     
     dugme1.onAction = (event: ActionEvent) =>{ 
       if (Math.abs(progress.getProgress -1) < 0.0001) {
Potera.kraj_druge = true
         if (Potera.takmicar.poeni >0) {
        Dvaprim
        }
       else {
         Finalna
      }
    }
       if (dugme1.text.value==pitanje1.to)
         Potera.takmicar.poeni = Potera.takmicar.poeni + 10000
       else
         Potera.takmicar.poeni = Potera.takmicar.poeni - 5000

   var check = true
     while( check){
     if (ucitano == broj_pitanja){
      val alert =new Alert(AlertType.Error) {
                  title = "Upozorenje!"
                  headerText = "Iscrpeli ste bazu pitanja."
                  contentText = ""
                }
     val result = alert.showAndWait()
       result match {
  case _  => Potera.stage.close()
}
    check =false 
     }
     br = (r.nextInt(broj_pitanja+1))
     if (Potera.array(br)!=1){
      check=false
      ucitano = ucitano + 1
     }
   }
  Potera.array(br)=1
    br = br*5
    pitanje1.p=Potera.niz_linija(br)
    pitanje1.to = Potera.niz_linija(br+1)
    pitanje1.po1 = Potera.niz_linija(br+2)
    pitanje1.po2=Potera.niz_linija(br+3)
    pitanje.text = pitanje1.p
    var br1 = r.nextInt(2)
       if (br1==0) {
        dugme1.text = pitanje1.to
        dugme2.text = pitanje1.po1
      }
      else {
        dugme1.text= pitanje1.po1  
        dugme2.text = pitanje1.to        
      }    
   zarada.text = Potera.takmicar.poeni.toString()
}

 dugme2.onAction = (event: ActionEvent) =>{
    if (Math.abs(progress.getProgress -1) < 0.0001) {
      Potera.kraj_druge = true
      if (Potera.takmicar.poeni >0) {
        Dvaprim
        }
       else {
         Finalna
      }
    }
   if (dugme2.text.value==pitanje1.to) 
     Potera.takmicar.poeni = Potera.takmicar.poeni + 10000
   else 
     Potera.takmicar.poeni = Potera.takmicar.poeni - 5000

   var check = true
     while( check){
     if (ucitano == broj_pitanja){
      val alert =new Alert(AlertType.Error) {
                 title = "Upozorenje!"
                  headerText = "Iscrpeli ste bazu pitanja."
                  contentText = ""
                }
     val result = alert.showAndWait()
       result match {
  case _  => Potera.stage.close()
}
    check =false 
     }
     br = (r.nextInt(broj_pitanja+1))
     if (Potera.array(br)!=1){
      check=false
      ucitano = ucitano + 1
     }
   }
  Potera.array(br)=1
    br = br*5
    pitanje1.p=Potera.niz_linija(br)
    pitanje1.to = Potera.niz_linija(br+1)
    pitanje1.po1 = Potera.niz_linija(br+2)
    pitanje1.po2=Potera.niz_linija(br+3)
    pitanje.text = pitanje1.p
    var br1 = r.nextInt(2)
       if (br1==0) {
        dugme1.text = pitanje1.to
        dugme2.text = pitanje1.po1
      //dugme3.text = pitanje1.po2
      }/*
      else if (br1==1) {
        dugme1.text= pitanje1.po1  
        dugme2.text = pitanje1.to 
        //dugme3.text = pitanje1.po1
      }*/
      else {
        dugme1.text= pitanje1.po1  
        dugme2.text = pitanje1.to 
        //dugme3.text = pitanje1.to        
      }    
   zarada.text = Potera.takmicar.poeni.toString()
}   
 
 
 dalje.onAction = (event: ActionEvent) =>{
     if (Math.abs(progress.getProgress -1) < 0.0001) {
       Potera.kraj_druge = true
      if (Potera.takmicar.poeni >0) {
        Dvaprim
        }
       else {
         Finalna
      }
    }
    var check = true
   while( check){
     if (ucitano == broj_pitanja){
      val alert =new Alert(AlertType.Error) {
                  title = "Upozorenje!"
                  headerText = "Iscrpeli ste bazu pitanja."
                  contentText = ""
                }
     val result = alert.showAndWait()
       result match {
  case _  => Potera.stage.close()
}
     check =false
   }
     br = (r.nextInt(broj_pitanja+1))
     if (Potera.array(br)!=1){
      check=false
      ucitano = ucitano + 1
     }
   }
  Potera.array(br)=1
    br = br*5
    pitanje1.p=Potera.niz_linija(br)
    pitanje1.to = Potera.niz_linija(br+1)
    pitanje1.po1 = Potera.niz_linija(br+2)
    pitanje1.po2=Potera.niz_linija(br+3)
    pitanje.text = pitanje1.p
    var br1 = r.nextInt(2)
       if (br1==0) {
        dugme1.text = pitanje1.to
        dugme2.text = pitanje1.po1
      }
      else {
        dugme1.text= pitanje1.po1  
        dugme2.text = pitanje1.to 
      }    
    }
}
  
   if (Potera.kraj_prve) {
    Potera.mediaPlayerTema.stop();
    Potera.stage.setScene(druga)  
    timeline.play()
   }
   
   }