<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/global.php";
  $current_page = 0;
?>
<?php include "include/navbar.php"; ?>
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Bienvenue sur SmartDoor Admin</h1>
          </div>
          <!--<p>Voici les 3 dernières notifications:</p>-->
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
<?php include "include/footer.php"; ?>
