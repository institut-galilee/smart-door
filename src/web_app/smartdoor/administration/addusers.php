<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/global.php";
  $current_page = 1;
  if(isset($_POST['addusers_nom']) && isset($_POST['addusers_prenom']) && isset($_POST['adusers_picture'])){


  }


?>

<?php include "include/navbar.php"; ?>
        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Ajout d'utilisateur</h1>
          </div>

          <div class="card shadow mb-4">
            <div class="card-header py-3">
              Personne
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-md-5">
                  <form action="addusers.php" method="post">
                   <div class="form-group">
                     <label for="addusers_nom">Nom:</label>
                     <input type="text" class="form-control" id="addusers_nom" name="addusers_nom">
                   </div>
                   <div class="form-group">
                     <label for="addusers_prenom">Prenom:</label>
                     <input type="text" class="form-control" id="addusers_prenom" name="addusers_prenom">
                   </div>
                   <div class="form-group">
                      <label for="adusers_picture">Lien photo:</label>
                      <input type="file" class="form-control-file border" id="adusers_picture" name="adusers_picture">
                  </div>
                   <button type="submit" class="btn btn-primary">Submit</button>
                  </form>
                </div>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

<?php include "include/footer.php"; ?>
