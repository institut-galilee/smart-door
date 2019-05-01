<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_name."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_name."/include/global.php";

  if (isset($_GET['retirer']))
  {
    if(!unlink($_GET['retirer']))
      echo "erreur lors de la suppression de l'image";
    else
    {
      echo "test";
      $query=$pdo->prepare('INSERT INTO logs (text) VALUES(:message)');
      $query->bindValue(':message',"L'utilisateur ".$user['prenom']." ".$user['nom']. " a retirer un utilisateur du serveur", PDO::PARAM_STR);
      $query->execute();
      $query->closeCursor();
      header ("Location: users.php");
    }
  }

  if(isset($_GET['ban']))
  {
    $file = "accepted_img/".$_GET['ban'];
    $newfile = "refuse_img/".$_GET['ban'];
    $error = copy($file, $newfile);
    if(!$error)
      echo "erreur lors du déplacement du fichier";
    else
    {
        $error = unlink($file);
        if(!$error)
          echo "erreur lors de la suppression";
        else
        {
          $query=$pdo->prepare('INSERT INTO logs (text, img) VALUES(:message, :img)');
  				$query->bindValue(':message',"L'utilisateur ".$user['prenom']." ".$user['nom']. " a banni un utilisateur", PDO::PARAM_STR);
  				$query->bindValue(':img',$newfile, PDO::PARAM_STR);
  				$query->execute();
  				$query->closeCursor();
          header ('Location: users.php');
        }
    }
  }

  if(isset($_GET['unban']))
  {
    $file = "refuse_img/".$_GET['unban'];
    $newfile = "accepted_img/".$_GET['unban'];
    $error = copy($file, $newfile);
    if(!$error)
      echo "erreur lors du déplacement du fichier";
    else
    {
        $error = unlink($file);
        if(!$error)
          echo "erreur lors de la suppression";
        else
        {
          $query=$pdo->prepare('INSERT INTO logs (text, img) VALUES(:message, :img)');
  				$query->bindValue(':message',"L'utilisateur ".$user['prenom']." ".$user['nom']. " a whitelisté un utilisateur banni", PDO::PARAM_STR);
  				$query->bindValue(':img',$newfile, PDO::PARAM_STR);
  				$query->execute();
  				$query->closeCursor();
          header ('Location: users.php');
        }
    }
  }
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
              Personnes acceptés
            </div>
            <div class="card-body">
              <div class="row" style="margin: 0 auto">
              <?php
              $dir = "accepted_img/";
              if (is_dir($dir))
              {
                if ($dh = opendir($dir))
                {
                  while(($file = readdir($dh)) !== false) {
                    if( $file != '.' && $file != '..' && preg_match('#\.(jpe?g|gif|png)$#i', $file))
                    {
                      ?>
                      <div class="card" style="width:12rem;">
                        <img class="card-img-top" src="accepted_img/<?php echo $file; ?>" alt="image" style="width:100%">
                        <div class="card-body">
                          <div class="row">
                            <a class="btn btn-primary" href="users.php?retirer=<?php echo $dir.$file; ?>" style="margin: 0 auto">Retirer</a>
                            <a class="btn btn-danger" href="users.php?ban=<?php echo $file; ?>" style="margin: 0 auto">Bannir</a>
                          </div>
                        </div>
                      </div>
                      <?php
                    }
                  }
                  closedir($dh);
                }
              }
              ?>
              </div>
            </div>
          </div>
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              Personnes refusés
            </div>
            <div class="card-body">
              <div class="row">
              <?php
              $dir = "refuse_img/";
              if (is_dir($dir))
              {
                if ($dh = opendir($dir))
                {
                  while(($file = readdir($dh)) !== false) {
                    if( $file != '.' && $file != '..' && preg_match('#\.(jpe?g|gif|png)$#i', $file))
                    {
                      ?>
                      <div class="card" style="width:13rem;">
                        <img class="card-img-top" src="refuse_img/<?php echo $file; ?>" alt="image" style="width:100%">
                        <div class="card-body">
                          <div class="row">
                            <a class="btn btn-success" href="users.php?unban=<?php echo $file; ?>" style="margin: 0 auto">Whitelister</a>
                            <a class="btn btn-primary" href="users.php?retirer=<?php echo $dir.$file; ?>" style="margin: 0 auto">Retirer</a>
                          </div>
                        </div>
                      </div>
                      <?php
                    }
                  }
                  closedir($dh);
                }
              }
              ?>
              </div>
            </div>
          </div>
        </div>

        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
<?php include "include/footer.php"; ?>
