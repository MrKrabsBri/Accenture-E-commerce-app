import React from "react";
import ItemCard from "./ItemCard";
import { Grid } from "@mui/material";

const itemList = [
  {
    itemName: "Sample Item 1",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    size: "M",
    price: "$19.99",
    imageUrl:
      "https://lp2.hm.com/hmgoepprod?set=format%5Bwebp%5D%2Cquality%5B79%5D%2Csource%5B%2F07%2F66%2F0766d7e6b642b035bb73ac48965394ae95b838f9.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_shirtsblouses_shirts%5D%2Ctype%5BDESCRIPTIVESTILLLIFE%5D%2Cres%5Bm%5D%2Chmver%5B2%5D&call=url%5Bfile%3A%2Fproduct%2Fmain%5D",
  },
  {
    itemName: "Sample Item 2",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    size: "L",
    price: "$24.99",
    imageUrl: "https://example.com/sample-image2.jpg",
  },
  {
    itemName: "Sample Item 3",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    size: "S",
    price: "$14.99",
    imageUrl: "https://example.com/sample-image3.jpg",
  },
];

const ItemList = () => {
  return (
    <Grid container spacing={2}>
      {itemList.map((item, index) => (
        <Grid item key={index} xs={12} sm={6} md={4}>
          <ItemCard item={item} />
        </Grid>
      ))}
    </Grid>
  );
};

export default ItemList;
