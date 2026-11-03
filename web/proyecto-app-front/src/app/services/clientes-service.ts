import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Cliente {
  id: number;
  nombre: string;
  correo: string;
  telefono: string;
  fechaRegistro: string;
}
@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  private baseUrl = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient) { }

  listar(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.baseUrl);
  }

  crear(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.baseUrl, cliente);
  }
  actualizar(id: number, cliente: Cliente) {
    return this.http.put<Cliente>(`${this.baseUrl}/${id}`, cliente);
  }
  eliminar(id: number) {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

}
