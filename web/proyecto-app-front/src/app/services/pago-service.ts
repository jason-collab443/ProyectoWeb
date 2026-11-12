import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Pago {
  id: number;
  referencia: string;
  facturaNumero: string;
  metodo: string;
  fecha: string;
}

export type DatosPago = Omit<Pago, 'id'>;

@Injectable({ providedIn: 'root' })
export class PagoService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8080/api/pagos';

  listar(): Observable<Pago[]> {
    return this.http.get<Pago[]>(this.baseUrl);
  }

  obtener(id: number): Observable<Pago> {
    return this.http.get<Pago>(`${this.baseUrl}/${id}`);
  }

  crear(pago: DatosPago): Observable<Pago> {
    return this.http.post<Pago>(this.baseUrl, pago);
  }

  actualizar(id: number, pago: DatosPago): Observable<Pago> {
    return this.http.put<Pago>(`${this.baseUrl}/${id}`, pago);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
