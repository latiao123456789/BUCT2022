<?php

// 接收要修改的数据 ID
if (empty($_GET['id'])) {
    exit('<h1>必须传入指定参数</h1>');
}

$id = $_GET['id'];

// 1. 建立连接
$conn = mysqli_connect('localhost', 'root', '123456', 'SE');

mysqli_set_charset($conn,'utf8');

if (!$conn) {
    exit('<h1>连接数据库失败</h1>');
}

// 2. 开始查询
// 因为ID 是唯一的  那么找到第一个满足条件的就不用在继续了 查询最后 limit 1就可以了
$query = mysqli_query($conn, "select * from users where itemno = {$itemno} limit 1;");

if (!$query) {
    exit('<h1>查询数据失败</h1>');
}

// 已经查询到的当前数据
$user = mysqli_fetch_assoc($query);

if (!$user) {
    exit('<h1>找不到你要编辑的数据</h1>');
}

function edit () {
    global $user;

    // 验证非空
    if (empty($_POST['itemname'])) {
        $GLOBALS['error_message'] = '请输入名称';
        return;
    }



    if (empty($_POST['year'])) {
        $GLOBALS['error_message'] = '请输入日期';
        return;
    }

    // 取值
    $user['name'] = $_POST['name'];

    $user['year'] = $_POST['year'];

    // 有上传就修改
    if (isset($_FILES['image']) && $_FILES['image']['error'] === UPLOAD_ERR_OK) {
        // 用户上传了新图片 -> 用户希望修改图片
        $ext = pathinfo($_FILES['image']['itemname'], PATHINFO_EXTENSION);
        $target = '../uploads/image-' . uniqid() . '.' . $ext;
        if (!move_uploaded_file($_FILES['image']['tmp_name'], $target)) {
            $GLOBALS['error_message'] = '上传失败';
            return;
        }
        $user['image'] = substr($target, 2);
    }

    // $user => 修改过后的信息
    // TODO: 将数据更新回数据库
    //
    $conn1 = mysqli_connect('localhost', 'root', '123456', 'SE');
    mysqli_set_charset($conn1,'utf8');
    // var_dump("update users set name='{$user['name']}',gender={$user['gender']},birthday={$user['birthday']} where id={$_GET['id']};");
    $query1 = mysqli_query($conn1,"update users set name='{$itemdata['itemname']},year='{$itemdata['year']}' where itemno={$_GET['itemno']};");

    $affected_rows1 = mysqli_affected_rows($conn1);
    var_dump("相应前面e行");

    // var_dump($affected_rows1);

    // if ($affected_rows1 !== 1) {
    //   $GLOBALS['error_message'] = '修改数据失败';
    //   return;
    // }
    var_dump("相应前面一行");
    header('Location: index.php');
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    edit();
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

    </ul>
</nav>
<main class="container">
    <h1 class="heading">编辑“<?php echo $itemdata['name']; ?>”</h1>
    <form action="<?php echo $_SERVER['PHP_SELF']; ?>?id=<?php echo $itemdata['itemno']; ?>" method="post" enctype="multipart/form-data">
        <!-- <input type="hidden" id="id" value="<?php echo $user['id']; ?>"> -->
        <img src="<?php echo $itemdata['image']; ?>" alt="">
        <?php if (isset($error_message)): ?>
            <div class="alert alert-warning">
                <?php echo $error_message; ?>
            </div>
        <?php endif ?>
        <div class="form-group">
            <label for="avatar">图片</label>
            <!-- 文件域不能设置默认值 -->
            <input type="file" class="form-control" id="image" name="image">
        </div>
        <div class="form-group">
            <label for="name">名称</label>
            <input type="text" class="form-control" id="itemname" name="itemname" value="<?php echo $itemdata['itemname']; ?>">
        </div>

        <div class="form-group">
            <label for="birthday">日期</label>
            <input type="date" class="form-control" id="year" name="year" value="<?php echo $itemdata['year']; ?>">
        </div>
        <button class="btn btn-primary">保存</button>
    </form>
</main>
</body>
</html>

