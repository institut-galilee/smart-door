<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_name."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_name."/include/global.php";

  $error = 0;
  if(isset($_GET['accept']))
  {
    $file = "pending_img/".$_GET['accept'];
    $newfile = "accepted_img/".$_GET['accept'];
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
  				$query->bindValue(':message',"L'utilisateur ".$user['prenom']." ".$user['nom']. " a accepté une personne", PDO::PARAM_STR);
  				$query->bindValue(':img',$newfile, PDO::PARAM_STR);
  				$query->execute();
  				$query->closeCursor();
          header ('Location: pending.php');
        }
    }
  }
  if(isset($_GET['refuse']))
  {
    $file = $folder_name."pending_img/".$_GET['refuse'];
    $newfile = $folder_name."refuse_img/".$_GET['refuse'];
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
  				$query->bindValue(':message',"L'utilisateur ".$user['prenom']." ".$user['nom']. " a refusé une personne", PDO::PARAM_STR);
  				$query->bindValue(':img',$newfile, PDO::PARAM_STR);
  				$query->execute();
  				$query->closeCursor();
          header ('Location: pending.php');
        }
    }
  }
?>
<?php include "include/navbar.php"; ?>
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Voir les demandes en cours</h1>
          </div>
          <div class="row">
          <?php
            $dir = "pending_img/";
            $i = 0;
            if (is_dir($dir))
            {
              if ($dh = opendir($dir))
              {
                while(($file = readdir($dh)) !== false) {
                  if( $file != '.' && $file != '..' && preg_match('#\.(jpe?g|gif|png)$#i', $file))
                  {
                    $i++;
                    ?>
                    <div class="card" style="width:13rem;">
                      <img class="card-img-top" src="pending_img/<?php echo $file; ?>" alt="image" style="width:100%">
                      <div class="card-body">
                        <div class="row">
                          <a class="btn btn-success" href="pending.php?accept=<?php echo $file; ?>" style="margin: 0 auto">Accpeter</a>
                          <a class="btn btn-danger" href="pending.php?refuse=<?php echo $file; ?>" style="margin: 0 auto">Refuser</a>
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
          <?php if ($i == 0) echo "Aucune demande en cours"; ?>
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
<?php include "include/footer.php"; ?>
