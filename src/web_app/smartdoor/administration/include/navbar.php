<!DOCTYPE html>
<html lang="fr">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SmartDoor - administration</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.php">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-lock"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SmartDoor</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">
      <!-- Nav Item - Dashboard -->
      <li class="nav-item <?php if($current_page==0) { ?> active <?php } ?>">
        <a class="nav-link" href="index.php">
          <i class="fas fa-fw fa-home"></i>
          <span>Accueil</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Menu
      </div>
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item <?php if($current_page==1) { ?> active <?php } ?>">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Gestion des utilisateurs</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="users.php">Voir les utilisateurs</a>
            <a class="collapse-item" href="addusers.php">Ajouter un utilisateur</a>
            <a class="collapse-item" href="pending.php">Voir les demandes</a>
            <a class="collapse-item" href="logs.php">Voir les logs</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item <?php if($current_page==2) { ?> active <?php } ?>">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>Paramètres</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="profile.php">Voir mon profile</a>
            <a class="collapse-item" href="setting.php">Changer de mot de passe</a>
            <a class="collapse-item" href="logout.php">Déconnexion</a>
          </div>
        </div>
      </li>
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">
            <?php //compteur pour les notifications
              $query=$pdo->prepare('SELECT * FROM logs ORDER BY id DESC LIMIT 1');
              $query->execute();
              $result = $query->fetch();
              $compteur_notif = $result[0];
              $diff_notif = $compteur_notif-$user['notification'];
            ?>
            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onclick="update_notification()">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <?php if($diff_notif>0) { ?>
                <span class="badge badge-danger badge-counter" id="alertNotification"><?php echo $diff_notif; ?></span>
                <?php } ?>
              </a>
              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Notifications
                </h6>
                <?php
                //on affiche le tableau
                $query=$pdo->prepare('SELECT * FROM logs ORDER BY id DESC LIMIT 3');
                $query->execute();
                while($data = $query->fetch(PDO::FETCH_ASSOC))
                {
                  if (file_exists($data['img']))
                    $img = $data['img'];
                  else
                    $img = "img/error.png";
                ?>
                <a class="dropdown-item d-flex align-items-center">
                  <div class="mr-3">
                    <img class="img-profile rounded-circle" src="<?php echo $img; ?>" width="75" height="75">
                  </div>
                  <div>
                    <div class="small text-gray-500"><?php echo $data['date']; ?></div>
                    <?php echo $data['text']; ?>
                  </div>
                </a>
                <?php } $query->closeCursor(); ?>
                <a class="dropdown-item text-center small text-gray-500" href="logs.php">Show All Alerts</a>
              </div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><?php echo $user['prenom']." ".$user['nom']; ?></span>
                <img class="img-profile rounded-circle" src="<?php echo $user['img_url']; ?>">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="profile.php">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Profile
                </a>
                <a class="dropdown-item" href="setting.php">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Settings
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="logout.php">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Déconnexion
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->
