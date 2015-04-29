<!--
function doSelected(isCheckBox,input){
	var input1 = document.all("DBID");
	var input2 = document.all("selectedIDs");
	var input3 = document.all("DBName");
	var input4 = document.all("selectedNames");
	var input1Val = "";
	var input3Val = "";
	if(isCheckBox) doChildNodeSelecte(input.value,input.checked);
	if (input1 == null) return;
	if (input1.length != null) {
		for(var i = 0; i <input1.length; i++) {
		  if (input1[i].checked == true) {
			input1Val += "," + input1[i].value;
			input3Val += "," + input3[i].value;
		  }
		}
	} else {
		if (input1.checked == true) {
		  input1Val += "," + input1.value;
		  input3Val += "," + input3.value;
		}
	}
	if (input1Val != null && input1Val.length >1 ) input1Val = input1Val.substring(1);
	if (input3Val != null && input3Val.length >1 ) input3Val = input3Val.substring(1);
	input2.value = input1Val;
	input4.value = input3Val;
}

function doConfirm() {
 // parent.document.all("").value=document.all("selectedIDs").value;
 // parent.document.all("").value=document.all("selectedNames").value;
  //parent.close_window();
}

function doChildNodeSelecte(parentId,state){
	//alert(parentId+"   "+state);
	var input1 = document.all("DBID");
	var input2 = document.all("ParentId");
	if(input2.length != null){
		for(var i = 0; i <input1.length; i++) {
			if(input2[i].value == parentId){
				doChildNodeSelecte(input1[i].value,state)
				input1[i].checked = state;
			}
		}
	}
}

function doSet(){
  var input1 = document.all("DBID");
  var input2 = document.all("selectedIDs");
  var input3 = document.all("DBName");
  var input4 = document.all("selectedNames");
  
  input2.value = "";
  input4.value = "";


  var input1Val = "";
  if(input1Val == null || input1Val.length < 1) return;
  input1Val = "," + input1Val + ",";
  if (input1 == null) return;
  var input1ItemValue = "";
  if (input1.length != null) {
    for(var i = 0; i <input1.length; i++) {
      input1ItemValue = ","+input1[i].value+",";
      if (input1Val.indexOf(input1ItemValue) != -1) {
        input1[i].checked = true;
      } else {
        input1[i].checked = false;
      }
    }
  } else {
    input1ItemValue = ","+input1.value+",";
    if (input1Val.indexOf(input1ItemValue) != -1) {
      input1.checked = true;
    } else {
      input1.checked = false;
    }
  }
}
//doSet();
//-->