        window.onload = function(){
            let check= document.getElementById("check");
            let stop   = document.getElementById("stop");
            let target = document.getElementById("target");
            let timer;

            check.onclick = function(){
                timer = setInterval(function(){
                        let now = new Date();
                        let h = now.getHours();
                        let m = now.getMinutes();
                        let s = now.getSeconds();
                        let currentTime = `${h} : ${m} : ${s}`;

                        target.innerHTML = currentTime; //현재 시간
                       
                    }, 1000);
            }
            stop.onclick = function(){
                clearInterval(timer);
              
            }
            
        }