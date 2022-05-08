<?php

// 1. 建立连接
$conn = mysqli_connect('localhost', 'root', '123456', 'se');

mysqli_set_charset($conn, 'utf8');

if (!$conn) {
    exit('<h1>连接数据库失败</h1>');
}

// 2. 开始查询
$query = mysqli_query($conn, 'select * from itemdata;');

if (!$query) {
    exit('<h1>查询数据失败</h1>');
}

// 3. 遍历结果集
// while ($item = mysqli_fetch_assoc($query)) {
//   $data[] = $item;
// }

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理系统</title>
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">后台管理系统</a>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="index.html">数据管理</a>
        </li>

    </ul>
</nav>
<main class="container">
    <h1 class="heading">数据管理 <a class="btn btn-link btn-sm" href="add.php">添加</a></h1>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>照片</th>
            <th>名称</th>

            <th>年份</th>
            <th class="text-center" width="140">操作</th>
        </tr>
        </thead>
        <tbody>
        <?php while ($itemdata = mysqli_fetch_assoc($query)): ?>
            <tr>
                <th scope="row"><?php echo $itemdata['itemno'] ?></th>
                <td><img src="<?php echo $itemdata['image']; ?>" class="rounded" alt="<?php echo $itemdata['itemname']; ?>"></td>
                <td><?php echo $itemdata['itemname']; ?></td>

                <td><?php echo $itemdata['year']; ?></td>
                <td class="text-center">
                    <a class="btn btn-info btn-sm" href="edit.php?id=<?php echo $itemdata['itemno'] ?>">编辑</a>
                    <a class="btn btn-danger btn-sm" href="delete.php?id=<?php echo $itemdata['itemno'] ?>">删除</a>
                </td>
            </tr>
        <?php endwhile ?>
        </tbody>
    </table>
    <ul class="pagination justify-content-center">
        <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
    </ul>
</main>
</body>
</html>

