package it.polito.tdp.sudoku.model;

public class SudokuSolver {

	public int[][] recursive(int[][] mm) {

		return recursive(mm, 0);
	}

	public int[][] recursive(int[][] mm, int passo) {

		// condizione di terminazione
		if (passo == 81) {
			return mm;
		}

		int riga = passo / 9;
		int colonna = passo % 9;

		// se la casella e' vuota
		if (mm[riga][colonna] == 0) {
			for (int val = 1; val < 10; val++) {
				// generaNuovaSoluzioneParziale;

				if (verifica(mm, riga, colonna, val)) {
				// if (check(mm, riga, colonna, val)) {
					mm[riga][colonna] = val;
					int ll[][] = recursive(mm, passo + 1);
					if (ll != null)
						return ll;
				}

				mm[riga][colonna] = 0;
			}
		} else {
			int ll[][] = recursive(mm, passo + 1);
			if (ll != null)
				return ll;
		}

		return null;

	}

	private boolean check(int[][] mm, int riga, int colonna, int current) {
		SudokuGenerator sg = new SudokuGenerator();
		return sg.legalMove(mm, riga, colonna, current);
	}

	private boolean verifica(int mm[][], int x, int y, int current) {

		for (int i = 0; i < 9; i++) {
			if (current == mm[x][i])
				return false;

		}

		for (int i = 0; i < 9; i++) {

			if (current == mm[i][y])
				return false;

		}

		int cornerX = 0;
		int cornerY = 0;

		if (x > 2) {
			if (x > 5)
				cornerX = 6;
			else
				cornerX = 3;
		}

		if (y > 2) {
			if (y > 5)
				cornerY = 6;
			else
				cornerY = 3;
		}

		for (int k = cornerX; k < 10 && k < cornerX + 3; k++) {
			for (int j = cornerY; j < 10 && j < cornerY + 3; j++) {

				if (current == mm[k][j])
					return false;
			}

		}

		return true;
	}

	// colonna==MODULO
	// riga==DIVISIONE
	// passo, caselle della matrice
	// copia matrice -> soluzione parziale
	// verifica(riga, colonna) posti

	public static void main(String[] args) {
		int mm[][] = new int[9][9];
		SudokuGenerator sg = new SudokuGenerator();
		mm = sg.nextBoard(45);

		SudokuSolver ss = new SudokuSolver();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(mm[i][j]);
			}
			System.out.print("\n");
		}

		System.out.print("\n+++++++++++++++++++++\n\n");

		int ll[][] = ss.recursive(mm);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(ll[i][j]);
			}
			System.out.print("\n");
		}
	}
}
