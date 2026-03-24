package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import carte.Carte;
import carte.JeuDeCartes;

import java.util.ListIterator;

public class GestionCartes {
	private static Random rand = new Random();
	
	public static <T> T extraire(List <T> liste) {
		int index=rand.nextInt(liste.size());
		return liste.remove(index);
	}
	
	public static <T> T extraireIterator(List <T> liste) {
		int index=rand.nextInt(liste.size());
		ListIterator<T> it = liste.listIterator();
		T elt=null;
		
		for (int i = 0; i < index ; i++) {
			elt = it.next();
		}
		it.remove();
		return elt;
	}
	
	public static <T> List<T> melanger(List <T> liste) {
		List<T> listeMelangee= new ArrayList<>();
		
		while(!liste.isEmpty()) {
			T element= extraire(liste);
			listeMelangee.add(element);
		}
		return listeMelangee;	
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		if(l1.size()!= l2.size())
			return false;
		
		for(int i=0; i<l1.size();i++) {
			T elt = l1.get(i);
			if(Collections.frequency(l1, elt) != Collections.frequency(l2,elt))
				return false;
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste){
		List<T> listeRassemblee= new ArrayList<>();
		
		while(!liste.isEmpty()) {
			T elt=liste.remove(0);
			listeRassemblee.add(elt);
			
			ListIterator<T> it=liste.listIterator();
			while(it.hasNext()) {
				T next= it.next();
				
				if(elt.equals(next)) {
					listeRassemblee.add(next);
					it.remove();
				}
			}
		}
		return listeRassemblee;
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
		ListIterator<T> it=liste.listIterator();
		
		T courant=it.next();
		while(it.hasNext()) {
			T next=it.next();
			
			if(!(courant.equals(next))) { 
				ListIterator<T> dup_finder= liste.listIterator(it.nextIndex()); 
				
				while(dup_finder.hasNext()) {
					if(courant.equals(dup_finder.next())){
						return false; 
					}
				}
			}
		}
		return true;
	}
	
	public static <T> void testerRassemblement(List<T> liste) {
		System.out.println("Verification de la liste :"+liste);
		boolean res=GestionCartes.verifierRassemblement(liste);
		System.out.println(" Liste bien rassemblee ?"+res);
	}
	

	
	
}