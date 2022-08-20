/**
 * 숫자 이외의 것이 입력되면 실행되는 팝업창 
 */
 
 function func(){
            let name = document.getElementById("name");
            let number = document.getElementById("number");
		
                
                if(name.value =='' ){					
                alert("이름은 필수입력사항입니다.");
				return false; 
                }
                
				if(number.value ==''){					
                alert("13자리의 숫자를 입력해주세요");
                return false; //값이 넘어가지 못하게 막음
                }
                
                return true;               
              
}
            
			
	
                
	
        

        
