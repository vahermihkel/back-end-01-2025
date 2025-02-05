import { CartProduct } from "./CartProduct"
import { Person } from "./Person"

export type Order = {
  id: number,
  person: Person,
  created: Date,
  totalSum: number,
  rows: CartProduct[]
}