var fileInput;
var submitButton;
var progressBarBoxContent;
var progressBar;
var tempProgressPercent='';

function refreshProgress(id) {
	buffalo.remoteCall("uploadMonitor.getUploadInfo",[id], function (replay){
    	updateProgress(replay.getResult(),id);
    	getProgressPercent(replay.getResult(),id);
    });    
}
function getProgressPercent(){
	return tempProgressPercent;
}
function setProgressPercent(index){
	tempProgressPercent = index;
}
function updateProgress(uploadInfo,id) {
//alert(uploadInfo.status);
	if (uploadInfo.status=="start"||uploadInfo.status=="progress") {
		progressBar.style.display = 'block';
//		fileInput.style.display = 'none';
		fileInput.disabled = true;
		submitButton.disabled = true;

		var fileIndex = uploadInfo.fileIndex;
		var progressPercent=0; 
		if(uploadInfo.totalSize!=0){
			progressPercent= Math.ceil((uploadInfo.bytesRead / uploadInfo.totalSize) * 100);
		}
        document.getElementById('progressBarText').innerHTML = 'upload in progress: ' + progressPercent + '%';
		//progressBarBoxContent.innerHTML = progressPercent + '%';
		setProgressPercent(progressPercent);
		progressBarBoxContent.style.width = parseInt(progressPercent * 3.5) + 'px';

		window.setTimeout("refreshProgress('"+id+"')", 1000);
	} else {
		submitButton.disabled = false;
		fileInput.disabled = false;
		progressBar.style.display = 'none';
	}
	return true;
}

function startProgress(fileInputName,submitButtonName,id) {
	//disable file input
	fileInput = document.getElementById(fileInputName);
	//disable submit button
	submitButton = document.getElementById(submitButtonName);
	progressBar = document.getElementById('progressBar');
	progressBarBoxContent = document.getElementById('progressBarBoxContent');
	if (fileInput != null && progressBar != null && progressBarBoxContent != null && submitButton!=null) {
		//progressBarBoxContent.innerHTML = '0%';
		window.setTimeout("refreshProgress('"+id+"')", 150);
		return true;
	} else {
		alert('Ajax Upload ERROR:some element of Ajax Upload is null!!');
		return false;
	}
}
