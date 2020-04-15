// $(function(){
//     $("#loginBtn").click(function(){
//         var username = $("#inputName").val();//取值
//         var password = $("#inputPassword").val();
//         if(!username){
//             alert("用户名必填!");
//             $("#username").focus();//获取焦点
//             return ;
//         }
//         if(!password){
//             alert("密码必填!");
//             $("#password").focus();//获取焦点
//             return ;
//         }
//         //var param = "username="+username+"&password="+password;
//         var param = {"username":username,"password":password};
//         $.post("/index/loginPost",param,function(result){
//             if(result){
//                 window.location.href="/home";
//             }else{
//                 alert("用户名或者密码错误!");
//             }
//         });
//     });
// });