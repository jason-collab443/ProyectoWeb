import { Routes } from '@angular/router';

import { HomeComponent } from './componentes/home-component/home-component';

export const routes: Routes = [
    {
        path: '', component: HomeComponent

    },
    {
        path: 'clientes',
        loadComponent: () =>
            import('./componentes/clientes-component/clientes-component')
                .then(c => c.ClientesComponent),
    },
    {
        path: 'detallesFacturas',
        loadComponent: () =>
            import('./componentes/detalle-factura-component/detalle-factura-component')
                .then(c => c.DetalleFacturaComponent),
    },
    {
        path: 'facturas',
        loadComponent: () =>
            import('./componentes/factura-component/factura-component')
                .then(c => c.FacturaComponent),
    },
    {
        path: 'movimientosInventario',
        loadComponent: () =>
            import('./componentes/inventario-movimiento-component/inventario-movimiento-component')
                .then(c => c.InventarioMovimientoComponent),
    },
    {
        path: 'ordenesCompra',
        loadComponent: () =>
            import('./componentes/orden-compra-component/orden-compra-component')
                .then(c => c.OrdenCompraComponent),
    },
    {
        path: 'pagos',
        loadComponent: () =>
            import('./componentes/pago-component/pago-component')
                .then(c => c.PagoComponent),
    },
    {
        path: 'perfiles',
        loadComponent: () =>
            import('./componentes/perfil-component/perfil-component')
                .then(c => c.PerfilComponent),
    },
    {
        path: 'permisos',
        loadComponent: () =>
            import('./componentes/permiso-component/permiso-component')
                .then(c => c.PermisoComponent),
    },
    {
        path: 'productos',
        loadComponent: () =>
            import('./componentes/producto-component/producto-component')
                .then(c => c.ProductoComponent),
    },
    {
        path: 'proveedores',
        loadComponent: () =>
            import('./componentes/proveedor-component/proveedor-component')
                .then(c => c.ProveedorComponent),
    },
    {
        path: 'roles',
        loadComponent: () =>
            import('./componentes/rol-component/rol-component')
                .then(c => c.RolComponent),
    },
    {
        path: 'sesiones',
        loadComponent: () =>
            import('./componentes/sesion-component/sesion-component')
                .then(c => c.SesionComponent),
    },
    

];
