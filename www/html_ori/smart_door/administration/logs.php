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
              <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Date</th>
                      <th>Text</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Id</th>
                      <th>Date</th>
                      <th>Text</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <?php
      							//on affiche le tableau
                    if(isset($_GET['full']))
                      $query=$pdo->prepare('SELECT * FROM logs ORDER BY id DESC');
                    else
      							  $query=$pdo->prepare('SELECT * FROM logs ORDER BY id DESC LIMIT 20');
      							$query->execute();
      							while($data = $query->fetch(PDO::FETCH_ASSOC))
      							{
      							?>
      							<tr>
      								<td><?php echo $data['id']; ?></td>
                      <td><?php echo $data['date']; ?></td>
                      <td><?php echo $data['text']; ?></td>
      							</tr>
      							<?php } $query->closeCursor(); ?>
                  </tbody>
                </table>
              </div>
              <?php if(!isset($_GET['full']))
              { ?>
              <a href="logs.php?full=1">Voir la liste complète</a>
            <?php } else { ?>
              <a href="logs.php">Voir la liste allégé</a>
              <?php } ?>
            </div>
          </div>
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
<?php include "include/footer.php"; ?>
