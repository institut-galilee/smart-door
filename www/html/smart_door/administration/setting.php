<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/global.php";
  if(isset($_POST['actual_pass']) && isset($_POST['password']) && isset($_POST['password2']))
  {
    $correctPassword = password_verify($_POST['actual_pass'], $user['password']); //le mot de passe actuel est valide
    echo $correctPassword;
    if($correctPassword)
    {
      if($_POST['password'] == $_POST['password2']) //les deux mots de passes sont identiques
      {
        if($_POST['password'] != $_POST['actual_pass']) //le nouveau mot de passe n'est pas le même que l'actuel
        {
          $hash = password_hash($_POST['password'], PASSWORD_DEFAULT);
          $query = $pdo->prepare('UPDATE users SET password = :new_password WHERE username = :username');
        	$query->bindParam(':username', $user["username"]);
          $query->bindParam(':new_password', $hash, PASSWORD_DEFAULT);
        	$query->execute();
          echo "valide";
        }
      }
    }
  }
  $current_page = 2;
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
              Changement de mot de passe
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-md-5">
                  <form action="setting.php" method="post">
                   <div class="form-group">
                     <label for="actual_pass">mot de passe actuel:</label>
                     <input type="password" class="form-control" id="actual_pass" name="actual_pass">
                   </div>
                   <div class="form-group">
                     <label for="password">nouveau mot de passe:</label>
                     <input type="password" class="form-control" id="password" name="password">
                   </div>
                   <div class="form-group">
                     <label for="password2">confirmation:</label>
                     <input type="password" class="form-control" id="password2" name="password2">
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
