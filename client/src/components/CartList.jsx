import React from "react";
import { Grid } from "@mui/material";
import Cart from "./Cart";

const CartList = ({ detailedItems, cartData, removeFromCart }) => {
  return (
    <div>
      {detailedItems.map((item, index) => (
        <Grid item key={index} xs={12} sm={6} md={3}>
          <Cart
            item={item}
            quantity={cartData[index].quantity}
            userId={cartData[index].userId}
            removeFromCart={removeFromCart}
          />
        </Grid>
      ))}
    </div>
  );
};

export default CartList;
