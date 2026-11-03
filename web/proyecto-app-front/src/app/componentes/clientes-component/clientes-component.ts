import { Component, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';


import { Cliente, ClientesService } from '../../services/clientes-service';

@Component({
  selector: 'app-clientes-component',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './clientes-component.html',
  styleUrls: ['./clientes-component.css']
})
export class ClientesComponent implements OnInit {

  clientes = signal<Cliente[]>([]);
  cargandoLista = signal(true);
  guardando = signal(false);
  error = signal<string | undefined>(undefined);
  editandoId = signal<number | null>(null);

  form!: FormGroup;

  constructor(private clientesService: ClientesService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      telefono: ['', [Validators.required, Validators.pattern(/^\d{7,15}$/)]],
      fechaRegistro: [new Date().toISOString().substring(0, 10), [Validators.required]]
    });
    this.cargarClientes();
  }

  cargarClientes() {
    this.cargandoLista.set(true);
    this.clientesService.listar().subscribe({
      next: data => { this.clientes.set(data); this.cargandoLista.set(false); },
      error: err => { console.error(err); this.error.set('Error al cargar clientes'); this.cargandoLista.set(false); }
    });
  }

  editar(c: Cliente) {
    this.editandoId.set(c.id);
    this.form.reset({
      nombre: c.nombre,
      correo: c.correo,
      telefono: c.telefono,
      fechaRegistro: c.fechaRegistro
    });
  }

  cancelarEdicion() {
    this.editandoId.set(null);
    this.form.reset({
      nombre: '',
      correo: '',
      telefono: '',
      fechaRegistro: new Date().toISOString().substring(0, 10)
    });
  }

  guardar() {
    if (this.form.invalid || this.guardando()) return;

    const payload: Cliente = this.form.value;
    this.guardando.set(true);

    const id = this.editandoId();
    if (id != null) {
      // === ACTUALIZAR ===
      this.clientesService.actualizar(id, payload).subscribe({
        next: actualizado => {
          this.clientes.update(arr => arr.map(x => x.id === id ? { ...actualizado } : x));
          this.cancelarEdicion();
          this.guardando.set(false);
        },
        error: err => { console.error(err); this.error.set('Error al actualizar cliente'); this.guardando.set(false); }
      });
    } else {
      // === CREAR ===
      this.clientesService.crear(payload).subscribe({
        next: creado => {
          this.clientes.update(arr => [...arr, creado]);
          this.cancelarEdicion();
          this.guardando.set(false);
        },
        error: err => { console.error(err); this.error.set('Error al guardar cliente'); this.guardando.set(false); }
      });
    }
  }

  eliminar(c: Cliente) {
    const confirmar = confirm(`¿Eliminar al cliente "${c.nombre}"?`);
    if (!confirmar) return;

    this.clientesService.eliminar(c.id).subscribe({
      next: () => {
        this.clientes.update(arr => arr.filter(x => x.id !== c.id));
      },
      error: err => {
        console.error(err);
        this.error.set('Error al eliminar cliente');
      }
    });
  }
}
