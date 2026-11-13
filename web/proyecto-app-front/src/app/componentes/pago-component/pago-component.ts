import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

import { DatosPago, Pago, PagoService } from '../../services/pago-service';

@Component({
  selector: 'app-pago-component',
  imports: [ReactiveFormsModule],
  templateUrl: './pago-component.html',
  styleUrl: './pago-component.css',
})
export class PagoComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly pagoService = inject(PagoService);

  readonly pagos = signal<Pago[]>([]);
  readonly pagosConFactura = computed(() => this.pagos().filter(p => !!p.facturaNumero?.trim()).length);
  readonly metodosUtilizados = computed(() => new Set(this.pagos().map(p => p.metodo.trim()).filter(Boolean)).size);
  readonly cargandoLista = signal(true);
  readonly guardando = signal(false);
  readonly eliminandoId = signal<number | null>(null);
  readonly editandoId = signal<number | null>(null);
  readonly error = signal<string | null>(null);

  readonly form = this.fb.nonNullable.group({
    referencia: ['', [Validators.required, Validators.maxLength(60)]],
    facturaNumero: ['', Validators.maxLength(40)],
    metodo: ['', [Validators.required, Validators.maxLength(40)]],
    fecha: ['', Validators.required],
  });

  ngOnInit(): void {
    this.cargarPagos();
  }

  cargarPagos(): void {
    this.error.set(null);
    this.cargandoLista.set(true);
    this.pagoService.listar().subscribe({
      next: pagos => {
        this.pagos.set(pagos);
        this.cargandoLista.set(false);
      },
      error: err => {
        console.error(err);
        this.error.set('No se pudieron cargar los pagos.');
        this.cargandoLista.set(false);
      },
    });
  }

  editar(pago: Pago): void {
    this.error.set(null);
    this.editandoId.set(pago.id);
    this.form.reset({
      referencia: pago.referencia,
      facturaNumero: pago.facturaNumero ?? '',
      metodo: pago.metodo,
      fecha: pago.fecha,
    });
  }

  cancelarEdicion(): void {
    this.editandoId.set(null);
    this.form.reset({ referencia: '', facturaNumero: '', metodo: '', fecha: '' });
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    if (this.guardando()) return;

    this.error.set(null);
    this.guardando.set(true);
    const datos: DatosPago = this.form.getRawValue();
    const id = this.editandoId();

    if (id !== null) {
      this.pagoService.actualizar(id, datos).subscribe({
        next: actualizado => {
          this.pagos.update(pagos => pagos.map(p => p.id === id ? actualizado : p));
          this.cancelarEdicion();
          this.guardando.set(false);
        },
        error: err => {
          console.error(err);
          this.error.set('No se pudo actualizar el pago.');
          this.guardando.set(false);
        },
      });
    } else {
      this.pagoService.crear(datos).subscribe({
        next: creado => {
          this.pagos.update(pagos => [...pagos, creado]);
          this.cancelarEdicion();
          this.guardando.set(false);
        },
        error: err => {
          console.error(err);
          this.error.set('No se pudo guardar el pago.');
          this.guardando.set(false);
        },
      });
    }
  }

  eliminar(pago: Pago): void {
    if (!confirm(`¿Eliminar el pago "${pago.referencia}"?`)) return;

    this.error.set(null);
    this.eliminandoId.set(pago.id);
    this.pagoService.eliminar(pago.id).subscribe({
      next: () => {
        this.pagos.update(pagos => pagos.filter(p => p.id !== pago.id));
        if (this.editandoId() === pago.id) this.cancelarEdicion();
        this.eliminandoId.set(null);
      },
      error: err => {
        console.error(err);
        this.error.set('No se pudo eliminar el pago.');
        this.eliminandoId.set(null);
      },
    });
  }
}
