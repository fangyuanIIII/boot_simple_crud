define(["js/jquery-3.5.1.js"],function (){
  "use strict";
  function company(options){
    this.options = options;
    this.init();
  }
  
  company.prototype.init=function(){
    var options = this.options;
    var href = options.href;
    var info = options.info;
    var switchBut = $("#switchBut")[0];
    var but = $("#but")[0];
    switchBut.onclick = function(){
      window.location.href='/'+href.toLowerCase();
    }
    
    if(href == "QUERY"){
     var localDate = new Date;
     var dateStr = localDate.toLocaleDateString();
     var dateInput = $("#dates")[0];
     dateInput.value=dateStr;
    }
    
    but.onclick = function(){
      if(href == "QUERY"){
        var name = $("#name").val();
        var dates = $("#dates").val();
        var state = $("#state").val();
  
        $.post("/addcompanydatas",
          {
            name:name,
            dates:dates,
            state:state
          },function(e){
           if(e != ""){
            alert(e);
           }else{
            alert("成功");
           }
          });
      }else{
        var name = $("#name").val();
        var size = $("#size").val();
        window.location.href='/'+info+'?name='+name+"&size="+size;
      }
    }
  }
  
  company.prototype.dispose=function(){
    this.options = null;
  }
  
  return{
    company : company
  }
})