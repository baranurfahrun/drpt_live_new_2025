<div class="sidebar">

    <!-- Sidebar user panel (optional) -->
    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
            <!-- <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image"> -->
        </div>
        <div class="info">
            <a href="#" class="d-block"><?php echo $_SESSION['nama']; ?></a>
            <a href="#" class="d-block">( <?php echo $_SESSION['jnj_jabatan']; ?> )</a>
        </div>
    </div>

    <!-- SidebarSearch Form -->
    <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
            <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
            <div class="input-group-append">
                <button class="btn btn-sidebar">
                    <i class="fas fa-search fa-fw"></i>
                </button>
            </div>
        </div>
    </div>

    <!-- Sidebar Menu -->
    <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
            <li class="nav-item menu-open">
                <a href="#" class="nav-link ">
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                    <p>
                        Dashboard
                        <i class="right fas fa-angle-left"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="index.php" class="nav-link ">
                            <i class="fa fa-plus-circle nav-icon"></i>
                            <p>Dashboard Utama</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="chart_ralan.php" class="nav-link ">
                            <i class="fa fa-user-plus nav-icon"></i>
                            <p>Dashboard Rawat jalan</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="chart_ranap.php" class="nav-link">
                            <i class="fa fa-bed nav-icon"></i>
                            <p>Dashboard Rawat Inap</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="apotik.php" class="nav-link">
                            <i class="fa fa-clipboard nav-icon"></i>
                            <p>Dashboard Apotik</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="chart_igd.php" class="nav-link">
                            <i class="fa fa-bed nav-icon"></i>
                            <p>Dashboard IGD</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="chart_jangdiang.php" class="nav-link">
                            <i class="nav-icon fas fa-copy"></i>
                            <p>Dashboard Jangdiag</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="chart_yansus.php" class="nav-link">
                            <i class="nav-icon fas fa-copy"></i>
                            <p>Dashboard Yansus</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="yanmed.php" class="nav-link">
                            <i class="nav-icon fas fa-copy"></i>
                            <p>Dashboard Yanmed</p>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a href="chart_smd.php" class="nav-link">
                            <i class="nav-icon fas fa-users"></i>
                            <p>Sumber daya Manusia</p>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a href="chart_antrian.php" class="nav-link">
                            <i class="nav-icon fas fa-heartbeat"></i>
                            <p>Antrian PoliKlinik</p>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a href="chart_tempat_tidur.php" class="nav-link">
                            <i class="nav-icon fas fa-bed"></i>
                            <p>Tempat Tidur</p>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a href="taskidbpjs.php" class="nav-link">
                            <i class="nav-icon fas fa-bed"></i>
                            <p>Data Task ID BPJS</p>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a href="logout.php" class="nav-link text-red text-bold">
                            <i class="nav-icon fas fa-sign-out-alt"></i>
                            <p>Logout</p>
                        </a>
                    </li>
                </ul>
            </li>

    </nav>
    <!-- /.sidebar-menu -->
</div>

</aside>