package potera

class Pitanje (var _p:String,var _to:String, var _po1:String, var _po2:String) {
//GETERI
  def p = _p
  def to = _to
  def po1 = _po1
  def po2 = _po2
//SETERI  
  def p_= (vrednost : String) : Unit = {
    _p = vrednost
  }
  def to_= (vrednost : String) : Unit = {
    _to = vrednost
  }
  def po1_= (vrednost : String) : Unit = {
    _po1 = vrednost
  }
  def po2_= (vrednost : String) : Unit = {
    _po2 = vrednost
  }
}