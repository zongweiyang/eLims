var mypd;
var opnode; 
var argStart;

function strConvert(codeStr)
{ 
   codeStr=rmfn(codeStr);
   var xmlDoc =  createMSXML(codeStr);
   xmlDoc.validateOnParse=false;
   var node=xmlDoc.documentElement;
   var result = "";
   node.normalize();
  return indft(node, 0);
}


function indft(node, pd)  // pd is operator precedence
{   var children, result="", value="";
	 
    if ( node == null ) return value;
    if ( node.nodeType == 3 )       // Mozilla supports Node.TEXT_NODE
    {   
        value = node.nodeValue.replace(/\s/g,""); 
        return value; 
    } 
    else if ( node.nodeType == 1 )  // Mozilla supports Node.ELEMENT_NODE
    {   
        if( node.tagName.match(/apply$/) || node.tagName.match(/reln$/) )
        { 
          var    children=node.childNodes;
          for (var i=0; i < children.length; i++)
          {    if ( children[i].nodeType == 1 ) 
	              { opnode= children[i];	              	
	                argStart = i+1;	               
		              break;
	               }
	        }
	         if (  children[argStart].tagName.match(/ci$/)|| children[argStart].tagName.match(/cn$/)||  children[argStart].tagName.match(/mstyle$/)||children[argStart].tagName.match(/semantics$/)||  children[argStart].tagName.match(/apply$/) ) 
           {  var  op=opnode.tagName.replace(/^[^:]*:/,"");
           	  if(isSetFunc(op))           	   
           	  {
           	  	var result= Set(node);
                return   result;
           	  }
            	else
           		{  
           			 var result=(infixExp(getOperator(opnode.tagName), children, argStart, pd));
           	     return   result;
      	      }
        	  }
	          else 
            {
               var result= domult(node);
               return   result;
        	  }
        }
        if ( node.tagName.match(/math$/) && node.hasChildNodes() || node.tagName.match(/mstyle$/) && node.hasChildNodes()) 
        { 
        	var children=node.childNodes;
	        for (var i=0; i < children.length; i++) 
             result += indft( children[i], pd );
          return result;
        }
        if ( node.tagName.match(/semantics$/) && node.hasChildNodes() ) 
        { 
        	var children=node.childNodes;
	        for (var i=0; i < children.length; i++) 
             result += indft( children[i], 0 );
          return result;
        }
        if ( node.tagName.match(/interval$/) && node.hasChildNodes()  )    //interval
        { 
        	var result=doIntreval(node);
          return result;
        }
        if ( node.tagName.match(/matrix$/) && node.hasChildNodes() )     //matrix
        {  
        	var result=doMatrix(node); 
          return result;
        }
        if ( node.tagName.match(/vector$/) && node.hasChildNodes())     //vector
        {  
        	var result=doVector(node);     
          return result;
        } 
        if ( node.tagName.match(/set$/) && node.hasChildNodes())     //set
        {  
        	var result=doSet(node);     
          return result;
        } 
        if ( node.tagName.match(/list$/) && node.hasChildNodes())     //list
        {  
        	var result=doList(node);     
          return result;
        } 
        
        if( ( node.tagName.match(/degree$/))&& node.hasChildNodes() )     
        {  
         var result1="^"+"(";
         var result2=")";
               
         var    children=node.childNodes;
	       for (var i=0; i < children.length; i++) 
             result += indft( children[i], pd );
             
             return result1+1+"/"+result+ result2;
        }
         if( ( node.tagName.match(/bvar$/)) && node.hasChildNodes() )     
        { 
         var  children=node.childNodes;
	       for (var i=0; i < children.length; i++) 
	       {   
	       	   if(node.previousSibling.tagName.match(/partialdiff$/)||node.previousSibling.tagName.match(/diff$/)||node.previousSibling.tagName.match(/forall$/)||node.previousSibling.tagName.match(/exists$/))
	       	        result += indft( children[i], pd );
	       	   else
	       	   	{
                result += indft( children[i], pd );
                result +="=";
              }
           }
             return result;
        }
        if( ( node.tagName.match(/logbase$/))&& node.hasChildNodes() )     
        { 
         var	result1="[";
         var  children=node.childNodes;
	       for (var i=0; i < children.length; i++) 
             result += indft( children[i], pd );
             result +="]"+"(";
             return result1+result;
        }
        if( ( node.tagName.match(/uplimit$/))&& node.hasChildNodes() )     
        { 
         var	result1="..";
         var  children=node.childNodes;
	       for (var i=0; i < children.length; i++) 
             result += indft( children[i], pd );
             return result1+result;
        }
        if( (( node.tagName.match(/lowlimit$/))||( node.tagName.match(/domainofapplication$/)))&& node.hasChildNodes() )     
        { 
         var  children=node.childNodes;
	       for (var i=0; i < children.length; i++) 
             result += indft( children[i], pd );
             return  result;
         }
        if ( node.tagName.match(/sep$/)) return "/";
        if ( node.tagName.match(/cn$/)) return doNumber(node);
        if ( node.tagName.match(/ci$/) && node.hasChildNodes() )
        { 
        	 var    children=node.childNodes;
        	 for (var i=0; i < children.length; i++) 
        	 {
        	   if(getxml(children[i]).match(/\u2205$/) )
        	     result +="{}" ; 
        	   else  result += indft( children[i], pd );
        	 }
        	 return result;
       	}
       	if ( node.tagName.match(/ci$/)  )
        { 
        	 return node.childNodes[0].nodeValue;  
        }else if(node.tagName.match(/fn$/))	 
       	{
       		return node.childNodes[0].nodeValue; 
       	}
	      else
        {   alert("Unrecognized: " + node.tagName);
	          return node.childNodes[0].nodeValue; 
        }
    }
}

function doNumber(node)
{   var ans="", argCount=0, result=new Array(), children= node.childNodes,
        args=children.length;
        
    if( args == 1 )
    {   return node.childNodes[0].nodeValue;   }
    if( node.getAttribute("type") == "rational" )
    {  for (var i=0; i < args; i++)
       {  
       	 ans=indft(children[i], 10);
           if ( ans != "" ) {  result.push(ans); argCount++; }
       }
       if ( argCount == 3 ) return ("("+result[0]+result[1]+result[2]+")");
    }
    return ("Unsupported Number");
}

function infixExp(operator, children, begin, pd)
{  var ans="", argCount=0, result=new Array(), args=children.length,
   mypd=operatorPrecedence(operator);
   for (var i=begin; i < args; i++)
   {   
   	  ans=indft(children[i], mypd);
      if ( ans != "" ) {  result.push(ans); argCount++; }
   }
   if ( argCount == 1 )
   { 
  	  if ( operator == "-" )  return(operator + result[0]);
      if ( operator == "+" )  return(result[0]);
      else return(operator + "("+ result[0]+")" );
   }
   ans=result[0];
   for (var i=1; i < argCount; i++)
   {  ans += nextTerm(operator, result[i]);   }
   return (( mypd > pd ) ? ans : ("("+ans+")"));
}

function nextTerm(operator, term)
{  if ( operator == "+" && term.charAt(0)=="-" )
      return term;
   else 
      return operator + term;
}

function getOperator(op)
{  op = op.replace(/^[^:]*:/,"");
   if ( op == "plus" ) return "+";
   if ( op == "eq" ) return "=";
   if ( op == "minus" ) return "-";
   if ( op == "times" ) return "*";
   if ( op == "divide" || op == "quotient" ) return "/";
   if ( op == "power" ) return "^";
   if ( op == "lt" ) return "<";
   if ( op == "gt" ) return ">";
   if ( op == "leq" ) return "<=";
   if ( op == "geq" ) return ">=";
   if ( op == "neq" ) return "!=";
   if ( op == "equivalent" ) return "==";  
   if ( op == "not" ) return "not";  
   if ( op == "root" ) return "sqrt";
   return op;
}

function operatorPrecedence(op)
{  if ( op == "+" || op == "-" ) return 2;
   if ( op == "*" || op == "/" ) return 5;
   if ( op == "=" ||op == ">" || op == "<"||op == ">=" || op == "<="  ) return 1;
   if ( op == "^" ) return 8;
   return 10;
}
function doIntreval(node)
 {
       
          var  children=node.childNodes;
          var  result=""; 
          	 if(node.getAttribute("closure")=="open")
        	    {
        	       var	result1="(";
        	       var  result2=")";
        	    }
        	    if(node.getAttribute("closure")=="open-closed")
        	    {
        	       var	result1="(";
        	       var  result2="]";
        	    }
        	    if(node.getAttribute("closure")=="closed-open")
        	    {
        	       var	result1="[";
        	       var  result2=")";
        	    }
        	    if(node.getAttribute("closure")=="closed")
        	    {
        	       var	result1="[";
        	       var  result2="]";
        	    }
	          for (var i=0; i < children.length; i++) 
             {
             	if(i<children.length-1)
             	{
             	  result+= indft( children[i], 10);
                result+=",";
                }
              else result += indft( children[i], 10 );
              }
             result="interval"+result1+result+result2;
 
           return result;
        
    }
    
function doMatrix(node)
{
	 var cnode=node.childNodes;
   var result1="([";
   var result2="])";
   var result="";
   for (var i=0; i < cnode.length; i++) 
	 {       result+="[" ;
	        if ( cnode[i].tagName.match(/matrixrow$/) && cnode[i].hasChildNodes() ) 
	         {
	         	     var children=cnode[i].childNodes;
	         	      for(var j=0; j< children.length; j++)
                  {   if(j< children.length-1)
                  	    {   result += indft( children[j], 10);
                               result+="," ;
                         }
                       else  result += indft( children[j],10 );
                  }
            }
           if(i < cnode.length-1)
              result+="]"+"," ;
           else     result+="]" ;  
     }  
           result="matrix"+result1+result+result2;
           return result;
	}

function doVector(node)
{ 
	  var  children=node.childNodes;  
	  var  result="";
	  var result1="([";
    var result2="])";
    for (var i=0; i < children.length; i++) 
    {
    	if(i< children.length-1)
      {   result += indft( children[i], 10);
          result+="," ;
      }
      else  result += indft( children[i],10 );
    }
    result="vector"+result1+result+result2;
    return result;
	}
function doSet(node)
{ 
	  var  children=node.childNodes;  
	  var  result="";
	  var result1="{";
    var result2="}";
    for (var i=0; i < children.length; i++) 
    {
    	if(i< children.length-1)
      {   result += indft( children[i], 10);
          result+="," ;
      }
      else  result += indft( children[i],10 );
    }
    result=result1+result+result2;
    return result;
	}	
function doList(node)
{ 
	  var  children=node.childNodes;  
	  var  result="";
	  var result1="[";
    var result2="]";
    for (var i=0; i < children.length; i++) 
    {
    	if(i< children.length-1)
      {   result += indft( children[i], 10);
          result+="," ;
      }
      else  result += indft( children[i],10 );
    }
    result=result1+result+result2;
    return result;
	}  
   
function domult(node)	
	{ var  children=node.childNodes;  
	  var  result="";
		var  op=opnode.tagName.replace(/^[^:]*:/,"");

    var	 result1="(";
    var  result2=")";
    
   if(children[0].tagName.match(/root$/)&&children[1].tagName.match(/degree$/))
   {
      	for (  var i=children.length-1; i>=argStart; i--) 
               result += indft( children[i],10 );        
          return   result;
    }
  
   if(children[0].tagName.match(/log$/))
   {
      	for (  var i=argStart; i<children.length; i++) 
          result += indft( children[i],10 );        
          return   op+result+result2;
   }
  
    if(children[0].tagName.match(/forall$/))
    {  
     	  var result=" ";
     	  var result1 = ","+"then " ;
     	      result1 += indft( children[children.length-1], 10 );
       	for (  var i=argStart; i<children.length-1; i++) 
	       {    
               result += indft( children[i],10 );
          }
          return   op+result+result1;

    }
    if(children[0].tagName.match(/exists$/))
    {  var result=" ";
     	  var result1 = " for which " ;
     	      result1 += indft( children[children.length-1], 10 );
       	for (  var i=argStart; i<children.length-1; i++) 
	       {    
               result += indft( children[i],10 );
          }
          return   op+result+result1;

    } 
    else
    { 
   		
   		 result += indft( children[children.length-1], 10 );
       result +=",";
  	  for (  var i=argStart; i<children.length-1; i++) 
	    {    
           result += indft( children[i], 10 );
      } 
       return   op+ result1+result+result2;
    }   
    
   }   
function Set(node) 
{
 	 var result1 = "" ;
 	 var result2 = "" ;
 	 var  children=node.childNodes;  	  
   var  op=opnode.tagName.replace(/^[^:]*:/,"");
	 var  K=argStart
   
   result1 = indft( children[K], 10 );
   result1 +=" ";
   K++;
   result2 +=" ";
   result2 += indft( children[K], 10 );                
   return  result1 + op+result2;
}    
   	
 function isSetFunc(str)
 {
	var tmp = str.toLowerCase();
	switch (str) {
		case "and"  :
		case "or"  :
		case "xor"  :
		case "implies"   :
		case "union"   :
		case "intersect"   :
		case "notsubset"   :
		case "notprsubset"   :
		case "subset"   :
		case "prsubset"   :
		case "in"   :
		case "notin"   :
		case "setdiff"   :
		case "cartesianproduct"   :
		 return true;

	}
	return false;
}  