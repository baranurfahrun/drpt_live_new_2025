        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" style="color:#ffffff" href="<?php echo base_url(); ?>"><?php echo config_item("AppsName")?></a>
            </div>
            <!-- /.navbar-header -->


            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <!--  <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                           
                        </li> -->
                        <li>
                            <a href="<?php echo base_url(); ?>"><i class="fa fa-home fa-fw"></i> Home</a>
                        </li>
                        <!-- <li>
                            <a href="#"><i class="fa fa-folder  fa-fw"></i> Bridge<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<?php echo base_url(); ?>catalog/ketersediaan_kamar">Ketersediaan Kamar</a>
                                </li>
                                 <li>
                                    <a href="<?php echo base_url(); ?>catalog/get_nik">Get NIK</a>
                                </li>
                                 <li>
                                    <a href="<?php echo base_url(); ?>catalog/registrasi_online">Registrasi Online</a>
                                </li>
                                <li>
                                    <a href="<?php echo base_url(); ?>catalog/laboratorium">Laboratorium</a>
                                </li>
                                
                            </ul>
                        <li> -->
                        <!-- <li>
                            <a href="#"><i class="fa fa-heartbeat  fa-fw"></i> e-Jantung<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<?php echo base_url(); ?>catalog/splash"><i class="fa fa-user  fa-fw"></i> Splash</a>
                                </li>
                                <li>
                                    <a href="<?php echo base_url(); ?>catalog/ketersediaan_kamar"><i class="fa fa-user  fa-fw"></i> Fasilitas</a>
                                </li>
                                <li>
                                    <a href="<?php echo base_url(); ?>catalog/ketersediaan_kamar"><i class="fa fa-user  fa-fw"></i> Tentang Kami</a>
                                </li>
                                <li>
                                    <a href="<?php echo base_url(); ?>catalog/ketersediaan_kamar"><i class="fa fa-user  fa-fw"></i> Panggilan Darurat</a>
                                </li>

                            </ul>
                        </li> -->
                        <!-- <li>
                            <a href="#"><i class="fa fa-hospital-o  fa-fw"></i> SIMRS<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<?php echo base_url(); ?>catalog/ketersediaan_kamar"><i class="fa fa-flask  fa-fw"></i> Laboratorium</a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa  fa-hospital-o fa-fw"></i> BPJS Kesehatan <span class="fa arrow"></span></a>
                                    <ul class="nav nav-second-level">
                                        <li>
                                            <a href="#"><i class="fa fa-circle  fa-fw"></i> Antrian Online<span class="fa arrow"></span></a>
                                            <ul class="nav nav-second-level">
                                                <li>
                                                    <a href="#"><i class="fa fa-circle fa-fw"></i> Status</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Create</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> View</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Cencel</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Queue Check In</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Patient New</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Operation Schedule</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Operation List</a>
                                                </li>

                                            </ul>

                                        </li>
                                    </ul>
                                </li> -->
                        <!-- <li>
                                    <a href="#"><i class="fa  fa-hospital-o fa-fw"></i> Dinas Kesehatan <span class="fa arrow"></span></a>
                                    <ul class="nav nav-second-level">
                                        <li>
                                            <a href="#"><i class="fa fa-circle  fa-fw"></i> Registrasi Online<span class="fa arrow"></span></a>
                                            <ul class="nav nav-second-level">
                                                <li>
                                                    <a href="#"><i class="fa fa-circle fa-fw"></i> Status</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Create</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> View</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Cencel</a>
                                                </li>
                                                <li>
                                                    <a href="#"><i class="fa fa-circle  fa-fw"></i> Patient New</a>
                                                </li>

                                            </ul>

                                        </li>
                                    </ul>
                                </li> -->
                        <!-- <li>
                                    <a href="#"><i class="fa  fa-neuter fa-fw"></i> Mmse Apps <span class="fa arrow"></span></a>
                                    <ul class="nav nav-second-level">
                                        <li>
                                            <a href="<?php echo base_url(); ?>catalog/mmse/pasien"><i class="fa fa-circle fa-fw"></i> Pasien</a>
                                        </li>
                                    </ul>
                                </li>

                            </ul>
                        </li> -->
                        <!-- <li>
                            <a href="<?php echo base_url(); ?>catalog/help"><i class="fa fa-question fa-fw"></i> Help</a>
                        </li> -->
                        <!-- /.nav-second-level -->
                        </li>

                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>