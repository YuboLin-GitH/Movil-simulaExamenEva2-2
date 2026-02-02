package com.example.gestfut_compose.navegacion


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.gestfut_compose.data.PartidoProveedor
import com.example.gestfut_compose.ui.components.BottomBar
import com.example.gestfut_compose.ui.components.MiTopBar
import com.example.gestfut_compose.ui.pantallas.PantallaDetalle


import com.example.gestfut_compose.ui.pantallas.pantallaCalendario
import com.example.gestfut_compose.ui.pantallas.pantallaClasificacion

//Aqui se define el NavHost y
//toda la navegación
@Composable
fun NavHost() {
    //Defino el el controlador de navegación
    val navController = rememberNavController()



    var refreshTrigger by remember { mutableStateOf(0) }


    Scaffold(
        topBar = { MiTopBar() },
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Calendario,
            modifier = Modifier.padding(padding)
        ) {
            //Defino las rutas, en este caso como String
            composable<Calendario> {
                // 读取 trigger，确保每次数据修改后，这个 composable 会重组
                val trigger = refreshTrigger
               /* var jornadas by remember { mutableStateOf(listOf("1","2","3","4","5")) }*/
                // 1. 获取数据里的轮次，并手动加上 "0"
                val jornadas = remember {
                    val listaReal = PartidoProveedor.partidos.map { it.jornada.toString() }.distinct().sorted()
                    listOf("0") + listaReal // <--- 关键修改：手动把 "0" 加到列表头
                }
                var jornada_sel by remember { mutableStateOf("0") }



                pantallaCalendario(
                    modificador= Modifier,
                    jornadas = jornadas, selectedJornada = jornada_sel,
                    {valor_spinner->jornada_sel=valor_spinner},
                    // 4. 传完整列表 (用 .toList() 确保每次 trigger 变化时都视为新列表)
                    partidos = PartidoProveedor.partidos.toList(),

                    // 5. 实现你同学定义的更新接口
                    onUpdatePartido = { local, visit, gLocal, gVisit ->
                        // 在数据源中找到该比赛并修改
                        val index = PartidoProveedor.partidos.indexOfFirst {
                            it.equipo_local == local && it.equipo_visitante == visit
                        }
                        if (index != -1) {
                            // 2. 【核心修改】创建一个副本 (Copy)，并填入新比分
                            // 这样 newPartido 就是一个全新的对象引用
                            val oldPartido = PartidoProveedor.partidos[index]
                            val newPartido = oldPartido.copy(
                                goles_local = gLocal,
                                goles_visitante = gVisit
                            )

                            // 3. 用新对象替换掉列表里的旧对象
                            PartidoProveedor.partidos[index] = newPartido

                            // 4. 触发刷新
                            refreshTrigger++
                        }
                    }
                     )
            }
            composable<Clasificacion> {
                pantallaClasificacion(
                    onEquipoClick = { nombre ->
                        // 点击时跳转到 DetalleEquipo，并传入名字
                        navController.navigate(DetalleEquipo(nombre))
                    }
                )
            }

            composable<DetalleEquipo> { backStackEntry ->
                // 1. 获取传递过来的参数
                val args = backStackEntry.toRoute<DetalleEquipo>()

                // 2. 显示详情页
                PantallaDetalle(
                    nombreEquipo = args.nombre,
                    onBackClick = { navController.popBackStack() } // 点击返回箭头，回退上一页
                )
            }


        }
    }
}

        /*
        partidos= if (jornada_sel == "0") {
                        PartidoProveedor.partidos
                    } else {
                        PartidoProveedor.partidos.filter { it.jornada == jornada_sel.toInt() }
                    },
                    onPartidoClick = { partido ->
                        partidoAEditar = partido // 记住点了哪个比赛
                        mostrarDialogo = true    // 打开弹窗

         */