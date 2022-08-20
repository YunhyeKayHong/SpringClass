
function validation(){
	let id = document.getElementById('memberid'); //히든
	let pw1 = document.getElementById('memberpw1'); //히든
	let pw2 = document.getElementById('memberpw2'); //히든

	
	if(pw1.value != pw2.value){
		alert('비밀번호를 정확하게 입력해주세요.');
				return false;		
	}
	
	if(pw1.value.length < 3 || pw1.value.length > 10){
		alert("비밀번호는 3~10자로 입력하세요.");
		return false;		
		
	}
	
	if(id.value.length < 3 || id.value.length > 10){
		alert("ID는 3~10자로 입력하세요.");
		return false;		
		
	}
	
	}
function idFormOpen(){
	window.open('idcheck','newWin','left=400, top=200, width=400, height=300, menubar=no');
	
}