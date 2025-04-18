import { Component } from '@angular/core';
import { Table } from '../../models/table.model';
import { TableService } from '../../services/table.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css'],
})
export class TableListComponent {

  tables: Table[] = [];

  newTable: Table = {
    numTab: 1,
    capacite: 4,
    statut: 'libre'
  };
  

  constructor(private tableService: TableService) {}

  ngOnInit(): void {
    this.getTables();
  }

  getTables(): void {
    this.tableService.getAllTables().subscribe(data => {
      this.tables = data;
    });
  }

  addTable(): void {
    this.tableService.createTable(this.newTable).subscribe(() => {
      this.getTables();
    });
  }
}
