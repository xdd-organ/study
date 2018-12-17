<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>Title</title>
    <script type="text/javascript" src="../mobile/stat/js/jquery/jqeury.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".header").click(function () {
                console.log("点击了头部");
                console.log(this);
                this.hide();
                // this.cssProps("height", "2rem")
            })
        })
    </script>
    <style type="text/css">
        .header {
            background: aqua;
            height: 1.5rem;
            text-align: center;
        }
        .abc {
            width: 5rem;
        }
    </style>
</head>
<body>
<div class="header">
    header
</div>

<div class="abc">
    用户名<br/>
密码</div>
<input type="text" name="username"/>
<input type="password" name="password"/>

</body>
</html>