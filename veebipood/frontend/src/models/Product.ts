import { Category } from "./Category"

export type Product = {
  id: number,
  name: string
  price: number,
  active: boolean,
  image: string,
  category: Category
}