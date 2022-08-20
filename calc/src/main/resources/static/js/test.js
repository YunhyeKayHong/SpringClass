/**
 * 숫자 이외의 것이 입력되면 실행되는 팝업창 
 */
 
 function func(){
            let num1 = document.getElementById("num1");
            let num2 = document.getElementById("num2");
		
				if(isNaN(num1.value) || num1.value ==''){					
                alert("숫자를 입력해주세요");
                num1.focus();
                num1.select();
                return false; //값이 넘어가지 못하게 막음
                }
                
                if(num2.value =='' || isNaN(num2.value)){					
                alert("숫자를 입력해주세요");
                num2.focus();
                num2.select();
				return false; 
                }
                
                return true;               
              
}
            
			
	
                
	
        

        
