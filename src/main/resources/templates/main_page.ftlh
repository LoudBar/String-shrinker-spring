<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
<form action="/shrink" method="POST">
    <input type="text" name="string">
    <input type="submit" value="shrink">
</form>
<form action="/unshrink" method="POST">
    <input type="text" name="string">
    <input type="submit" value="unshrink">
</form>
<#if error??>
    <p>${error}</p>
<#else>
    <#if out??>
        <p>${out}</p>
    </#if>
</#if>
</body>
</html>