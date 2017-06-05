package potera

class Takmicar {  
  
//GETERI  
  def ime = _ime;
  def poeni = _poeni;
  def index = _index;
//SETERI
  def ime_= (value:String):Unit = { 
    _ime = value 
  }

  def index_= (value:Int):Unit = { 
    _index = value 
  }
  
  def poeni_= (value:Int):Unit = { 
    _poeni = value 
  }
  

  private var _ime = "";
  private var _poeni = 0;
  private var _index = 0;
}