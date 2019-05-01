<body>
	<center>
		<header>
			<h1>LOGUEO</h1>
			<hr />
		</header>
		<aside>
			<div style="color:red">
				<?php if(isset($_SESSION['error'])) {
					if(is_array($_SESSION['error'])) {
						foreach($_SESSION['error'] as $error) {
							echo $error; ?><br /><?php
						}
					} else
						echo $_SESSION['error'];?><br /><?php
					
					unset($_SESSION['error']);
				} ?>
			</div>
			<form method="post" action="security_check">
				usuario: <input name="user" type="text" />
				contrase&ntilde;a: <input name="password" type="password" />
				<br />
				<input type="submit" value="ENTRAR" />
			</form>
		</aside>
	</center>
</body>
</html>