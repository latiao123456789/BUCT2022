<?php

function add_user() {
    // 验证非空
    if (empty($_POST['itemname'])) {
        $GLOBALS['error_message'] = '请输入名称';
        return;
    }

    /*if (!(isset($_POST['gender']) && $_POST['gender'] !== '-1')) {
        $GLOBALS['error_message'] = '请选择性别';
        return;
    }*/

    if (empty($_POST['year'])) {
        $GLOBALS['error_message'] = '请输入日期';
        return;
    }

    // 取值
    $itemname = $_POST['itemname'];
    //$gender = $_POST['gender'];
    $year = $_POST['year'];

    // 接收文件并验证
    if (empty($_FILES['image'])) {
        $GLOBALS['error_message'] = '请上传图片';
        return;
    }

    $ext = pathinfo($_FILES['image']['name'], PATHINFO_EXTENSION);
    // => jpg
    $target = '../uploads/image-' . uniqid() . '.' . $ext;

    if (!move_uploaded_file($_FILES['image']['tmp_name'], $target)) {
        $GLOBALS['error_message'] = '上传图片失败';
        return;
    }

    $avatar = substr($target, 2);

    // var_dump($name);
    // var_dump($gender);
    // var_dump($birthday);
    // var_dump($avatar);
    // 保存

    // 1. 建立连接
    $conn = mysqli_connect('localhost', 'root', '123456', 'SE');

    if (!$conn) {
        $GLOBALS['error_message'] = '连接数据库失败';
        return;
    }

    // var_dump("insert into users values (null, '{$name}', {$gender}, '{$birthday}', '{$avatar}');");
    // 2. 开始查询
    $query = mysqli_query($conn, "insert into users values (null, '{$itemname}', '{$year}', '{$image}');");

    if (!$query) {
        $GLOBALS['error_message'] = '查询过程失败';
        return;
    }

    $affected_rows = mysqli_affected_rows($conn);

    if ($affected_rows !== 1) {
        $GLOBALS['error_message'] = '添加数据失败';
        return;
    }

    // 响应
    header('Location: index.php');
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    add_user();
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>数据管理系统</title>
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">数据管理系统</a>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="index.html">数据管理</a>
        </li>
        <li class="nav-item">

        </li>
    </ul>
</nav>
<main class="container">
    <h1 class="heading">添加数据</h1>
    <?php if (isset($error_message)): ?>
        <div class="alert alert-warning">
            <?php echo $error_message; ?>
        </div>
    <?php endif ?>
    <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post" enctype="multipart/form-data" autocomplete="off">
        <div class="form-group">
            <label for="image">图片</label>
            <input type="file" class="form-control" id="image" name="image">
        </div>
        <div class="form-group">
            <label for="name">名称</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>

        <div class="form-group">
            <label for="year">日期</label>
            <input type="varchar" class="form-control" id="year" name="year">
        </div>
        <button class="btn btn-primary">保存</button>
    </form>
</main>
</body>
</html>
