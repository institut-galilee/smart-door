<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_name."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_name."/include/global.php";
?>
<?php include "include/navbar.php"; ?>
        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          </div>
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              Mon profile
            </div>
            <div class="card-body">
              <p>
                Login: <b><?php echo $user['username']; ?></b><br>
                Prenom: <b><?php echo $user['prenom']; ?></b><br>
                Nom: <b><?php echo $user['nom']; ?></b><br>
                Url image: <b>"<?php echo $user['img_url']; ?>"</b>
              </p>
            </div>
          </div>
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
<?php include "include/footer.php"; ?>
