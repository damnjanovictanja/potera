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


object Treca {
  

  Potera.mediaPlayerTreca.setOnEndOfMedia(new Runnable(){
        def run:Unit={
          Potera.mediaPlayerTreca.seek(Duration.ZERO)
        }
      })
  
  var progress1 = new ProgressBar {
    minWidth = 50
  }
  
  
  
   def formatElapsed(elapsed: Double): String = {
    val seconds = (elapsed / 10.0).floor
    val dezipart = elapsed % 10
    seconds.toInt + "." + dezipart.toInt + "s"
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
  
  
//  val Potera.niz_linija = Source.fromFile("qf.txt").getLines.toArray
  val broj_pitanja = (Potera.niz_linija.length)/5;  
  val r = scala.util.Random 
 // var array = Array.fill(broj_pitanja+1){0}
   //   var br = (r.nextInt(broj_pitanja+1)) 
  //  array(br)=1
  //  br=br*5
   
    var indextr = 8
        
      
     var rb_pitanja=0
     var check = true
      while( check){
     if (Druga.ucitano == broj_pitanja){
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
     rb_pitanja = (r.nextInt(broj_pitanja+1))
     if (Potera.array(rb_pitanja)!=1){
      check=false
      Druga.ucitano = Druga.ucitano + 1
     }
   }  
 
    Potera.array(rb_pitanja)=1
    rb_pitanja=rb_pitanja*5
    
    var pitanje1 = new Pitanje(Potera.niz_linija(rb_pitanja),Potera.niz_linija(rb_pitanja+1), Potera.niz_linija(rb_pitanja+2), Potera.niz_linija(rb_pitanja+3))
    
    pitanje1.p=Potera.niz_linija(rb_pitanja)
    pitanje1.to = Potera.niz_linija(rb_pitanja+1)
    pitanje1.po1 = Potera.niz_linija(rb_pitanja+2)
    pitanje1.po2=Potera.niz_linija(rb_pitanja+3)
    
    
    
  var treca = new Scene{
    
      timeline1.play()
  
    Potera.mediaPlayerTreca.play()
    val pitanje3 = new Label {
      text = pitanje1.p
      alignment=Pos.CENTER
      style = """-fx-font: normal italic 15pt sans-serif;
                 -fx-text-fill: #fff"""
    }
    
    val dugmence = new Button("Sledece pitanje"){
           style="""-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
      prefWidth = 100
      prefHeight = 50
       disable=true
         }
    
    val odgovor1 = new Button {
      style="""-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
      prefWidth = 400
      prefHeight = 50
    }  
    
    val odgovor2 = new Button {
      style="""-fx-background-color:  linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
      prefWidth = 400
      prefHeight = 50
    } 
    val odgovor3 = new Button {
      style="""-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
      prefWidth = 400
      prefHeight = 50
    }
    var br1 = r.nextInt(2)
      if (br1==0) {
        odgovor1.text = pitanje1.to
        odgovor2.text = pitanje1.po1
        odgovor3.text = pitanje1.po2
      }
      else if (br1==1) {
        odgovor1.text= pitanje1.po2  
        odgovor2.text = pitanje1.to 
        odgovor3.text = pitanje1.po1
      }
      else {
        odgovor1.text= pitanje1.po1  
        odgovor2.text = pitanje1.po2 
        odgovor3.text = pitanje1.to        
      }     
         
      var sadrzaj = new VBox { 
        minWidth=1000  
        spacing = 50
        padding = Insets(50)
        alignment = Pos.CENTER
        alignmentInParent = Pos.CENTER
        val vBox = new VBox { 
          spacing = 10
          alignment = Pos.CENTER
          alignmentInParent = Pos.CENTER
          children.addAll(odgovor1, odgovor2, odgovor3)
        }
     
       val tajmer = new HBox { 
         spacing = 20
         alignment = Pos.CENTER
         alignmentInParent = Pos.CENTER
         var vreme_label = new Label {
           text = "Vreme: "
           style =  """-fx-font-size: 15pt;
           -fx-text-fill: #727489;"""
         }
         
       children.addAll(vreme_label, progress1, numericProgress1, dugmence)
       }
      
  children.addAll(pitanje3, vBox, tajmer)
    }     
      
          val b1 = new Button{
            id = "1"
            text = "1"
            prefWidth = 60
            prefHeight = 30
            if (Potera.takmicar.index>=1)
              style = """-fx-background-color: linear-gradient(#ffffff, #5963c1);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           val b2 = new Button{
             id = "2"
             text = " 2"
             prefWidth = 70
            prefHeight = 30
             if (Potera.takmicar.index>=2)
              style = """-fx-background-color: linear-gradient(#ffffff, #5963c1);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           val b3 = new Button{
             id = "3"
             
             text = "3"
             prefWidth = 80
             prefHeight = 30
              if (Potera.takmicar.index>=3)
              style = """-fx-background-color: linear-gradient(#ffffff, #5963c1);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           val b4 = new Button{
            id = "4"
            
             text = " 4"
             prefWidth = 90
            prefHeight = 30
            if (Potera.takmicar.index>=4)
              style = """-fx-background-color: linear-gradient(#ffffff, #5963c1);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           val b5 = new Button{
            id = "5"
            
             text = " 5"
             prefWidth = 100
            prefHeight = 30
             if ( Potera.takmicar.index >=5)
              style = """-fx-background-color: linear-gradient(#ffffff, #5963c1);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           val b6 = new Button{
            id = "6"
            
             text = " 6"
             prefWidth = 110
            prefHeight = 30 
             if (Potera.takmicar.index >= 6)
              style = """-fx-background-color: linear-gradient(#ffffff, #5963c1);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           val b7 = new Button{
             id = "7"
             
             text = "7"
             prefWidth = 120
            prefHeight = 30
             if (Potera.takmicar.index >= 7)
              style = """-fx-background-color: linear-gradient(#ffffff, #5963c1);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
      
        var box2 = new VBox{
          padding = Insets(120)
          children.addAll(b7,b6,b5,b4,b3,b2,b1)
        }
      var odgovor_labela = new Label ("")
      var odgovor_tragaca = new Label("")
      sadrzaj.children.addAll(odgovor_labela,odgovor_tragaca)
        
      var h_box = new HBox{
        spacing = 100
        //padding = Insets(50, 80, 50, 50)
        
        children.addAll(sadrzaj,box2);
     } 
   
     var canvas = new Canvas (1000,600)
     var gc = canvas.graphicsContext2D;

     gc.drawImage(new Image(Potera.slika_url), 0, 0);
     content= List(canvas,box2, h_box)
      
      dugmence.onAction = (event: ActionEvent) =>{
        
       odgovor_labela.text = ""
       odgovor_tragaca.text = ""
              
       Potera.takmicar.index match {
         case 1 => { b2.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca); """
           b1.style = """-fx-background-color: linear-gradient(#ffffff, #2714a0);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
         case 2 => { b3.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca); """
           b2.style = """-fx-background-color: linear-gradient(#ffffff,#2714a0);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
           case 3 => { b4.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca); """
             b3.style = """-fx-background-color: linear-gradient(#ffffff, #2714a0);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
            case 4 => {
              
       b5.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         b4.style = """-fx-background-color: linear-gradient(#ffffff, #2714a0);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
             case 5 => { b6.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);"""
               b5.style = """-fx-background-color: linear-gradient(#ffffff, #2714a0);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
              case 6 => { 
                b6.style = """-fx-background-color: linear-gradient(#ffffff, #2714a0);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
              case _ => {
                b6.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca)"""
                b7.style = """-fx-background-color: linear-gradient(#ffffff,#2714a0) ;
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
       }
       
       indextr match {
         
         case 1 => { b1.style = """-fx-background-color: linear-gradient(#f78a8a, #c10707);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
         case 2 => {b2.style = """-fx-background-color: linear-gradient(#f78a8a, #c10707);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
           case 3 => { b3.style = """-fx-background-color: linear-gradient(#f78a8a, #c10707);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
            case 4 => { b4.style = """-fx-background-color: linear-gradient(#f78a8a, #c10707);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
             case 5 => { b5.style = """-fx-background-color: linear-gradient(#f78a8a, #c10707);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
              case 6 => { b6.style = """-fx-background-color: linear-gradient(#f78a8a, #c10707);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }
              case _ => { b7.style = """-fx-background-color: linear-gradient(#ffffff, #c10707);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
         }  
       }
       
       if (Potera.takmicar.index == indextr ){
         Potera.kraj_trece = true
         Potera.takmicar.poeni = 0
         Finalna
       }
       if (Potera.takmicar.index == 0){
         Potera.kraj_trece = true
         Finalna
       }
       
        odgovor2.disable = false
           odgovor1.disable = false
           odgovor3.disable = false
           dugmence.disable = true
          odgovor1.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           odgovor2.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
            odgovor3.style = """-fx-background-color: linear-gradient(#ffffff, #cecaca);
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
      
       var br = 0
       var check = true
    while( check){
     if (Druga.ucitano == broj_pitanja){
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
      Druga.ucitano = Druga.ucitano + 1
     }
   }  
  Potera.array(br)=1
    br = br*5
    pitanje1.p=Potera.niz_linija(br)
    pitanje1.to = Potera.niz_linija(br+1)
    pitanje1.po1 = Potera.niz_linija(br+2)
    pitanje1.po2=Potera.niz_linija(br+3)
    pitanje3.text = pitanje1.p
    var br1 = r.nextInt(3)
       if (br1==0) {
        odgovor1.text = pitanje1.to
        odgovor2.text = pitanje1.po1
        odgovor3.text = pitanje1.po2
      }
      else if (br1==1) {
        odgovor1.text= pitanje1.po2 
        odgovor2.text = pitanje1.to 
        odgovor3.text = pitanje1.po1
      }
      else {
        odgovor1.text= pitanje1.po1  
        odgovor2.text = pitanje1.po2 
        odgovor3.text = pitanje1.to        
      } 
        //print (numericProgress1.text)
        elapsed1 = DoubleProperty(0)
        progress1.progress <== elapsed1 / 100
        numericProgress1.text <== Bindings.createStringBinding(new Callable[String] { override def call(): String =
    formatElapsed(elapsed1())
    }, elapsed1)
    timeline1.stop()
    timeline1 = Timeline(KeyFrame(Duration(10), "", (e: ActionEvent) =>
    if (elapsed1() < 100) elapsed1() = elapsed1() + 0.1))
    timeline1.setCycleCount(Timeline.INDEFINITE)
   
    timeline1.play()   
      }
     
      
   odgovor1.onAction = (event: ActionEvent)=>{
     timeline1.stop()     
     odgovor2.disable = true
           odgovor1.disable = true
           odgovor3.disable = true
           dugmence.disable = false
           val ver = r.nextInt(101)
      if (ver<94){ 
        odgovor_tragaca.text ="Odgovor tragaca: " + pitanje1.to
        indextr = indextr - 1
      }
      else if (ver > 95) 
        odgovor_tragaca.text ="Odgovor tragaca: "  + pitanje1.po1
      else 
        odgovor_tragaca.text = "Odgovor tragaca: "+ pitanje1.po2
           if (numericProgress1.text.value !="10.0s"){
           if (odgovor1.text.value==pitanje1.to) {
           odgovor_labela.text = "TACAN ODGOVOR"
           Potera.takmicar.index = Potera.takmicar.index - 1
               odgovor1.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           else {
           odgovor_labela.text = "NETACAN ODGOVOR"
           //IZMENITI DA SE DUGME OBOJI U CRVENO
               odgovor1.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill:  #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           }
           else {
             //BOJI SE U CRVENO
            odgovor_labela.text = "NISTE ODGOVORILI NA VREME."
               odgovor1.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
             
             
           }
           
      
           
         }
   
     odgovor2.onAction = (event: ActionEvent)=>{
       timeline1.stop()  
       odgovor2.disable = true
           odgovor1.disable = true
           odgovor3.disable = true
           dugmence.disable=false
           val ver = r.nextInt(101)
           if (ver<94) {
        odgovor_tragaca.text ="Odgovor tragaca: " + pitanje1.to
        indextr = indextr - 1
           }
      else if (ver > 95) 
        odgovor_tragaca.text ="Odgovor tragaca: "  + pitanje1.po1
      else 
        odgovor_tragaca.text = "Odgovor tragaca: "+ pitanje1.po2
           if (numericProgress1.text.value !="10.0s"){
           if (odgovor2.text.value==pitanje1.to) {
           odgovor_labela.text = "TACAN ODGOVOR"
           Potera.takmicar.index = Potera.takmicar.index - 1
               odgovor2.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           else {
           odgovor_labela.text = "NETACAN ODGOVOR"
           //IZMENITI DA SE DUGME OBOJI U CRVENO
               odgovor2.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill:  #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           }
           else {
             //BOJI SE U CRVENO
            odgovor_labela.text = "NISTE ODGOVORILI NA VREME."
               odgovor2.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
             
             
           }
       
        
         }
     
     odgovor3.onAction = (event: ActionEvent)=>{
         timeline1.stop()
           odgovor2.disable = true
           odgovor1.disable = true
           odgovor3.disable = true
           dugmence.disable=false
          
        val ver = r.nextInt(101)
           if (ver < 90){ 
        odgovor_tragaca.text ="Odgovor tragaca: " + pitanje1.to
           indextr = indextr - 1
           }
        else if (ver > 95) 
        odgovor_tragaca.text ="Odgovor tragaca: "  + pitanje1.po1
      else 
        odgovor_tragaca.text = "Odgovor tragaca: "+ pitanje1.po2
           if (numericProgress1.text.value !="10.0s"){
           if (odgovor3.text.value==pitanje1.to) {
           odgovor_labela.text = "TACAN ODGOVOR"
           Potera.takmicar.index = Potera.takmicar.index - 1    
           odgovor3.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           else {
           odgovor_labela.text = "NETACAN ODGOVOR"
           //IZMENITI DA SE DUGME OBOJI U CRVENO
               odgovor3.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill:  #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
           }
           }
           else {
             //BOJI SE U CRVENO
            odgovor_labela.text = "NISTE ODGOVORILI NA VREME."
               odgovor3.style ="""-fx-background-color: linear-gradient(#ecef99 0%, #e8ed6d 50%, #ebf243 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5;
      -fx-background-insets: 0,1,2,3,0;
      -fx-text-fill: #727489;
      -fx-font-weight: bold;
      -fx-font-size: 15px;
      -fx-padding: 5 10 5 10;"""
             
             
           } 
       
         }
    }
       if(Potera.kraj_dvaPrim){
      Potera.mediaPlayerDvaPrim.stop()
      Potera.stage.setScene(treca)
      timeline1.play()
       
      }
    
  }
