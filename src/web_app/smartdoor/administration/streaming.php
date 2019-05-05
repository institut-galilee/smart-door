<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/global.php";
?>
<?php include "include/navbar.php"; ?>
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Flux vidéo</h1>
          </div>

              <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/navbar-bottom/">

              <!-- Bootstrap core CSS -->
          <link href="/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">




              <script>

                function myFunction(){ setInterval(takePicture , 3000);}
                function takePicture() {
                                        wndw= window.open('http://192.168.43.171/jpg','popup','width=60,height=60');
                                        setTimeout(function() {
                                            location.reload(true);
                                          }, 3000);

                                        setTimeout(function() {
                                            wndw.close();
                                          }, 2000);
                                        }

              </script>

            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
            </head>
            <body onload="myFunction()">
              <div class="container">
            <div class="jumbotron mt-3">
              <h1>Live streaming</h1>


              <img src="http://192.168.43.171/jpg_stream"   width="540" height="380"/>

            </div>
          </div>

          <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script></body>
          </html>









        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->
<?php include "include/footer.php"; ?>
