import React from "react";
import { useLocation } from "react-router-dom";
import Navbar from "../../components/Navbar";
import UpdateItemForm from "../../components/UpdateItemForm";

const UpdateItemPage = () => {
  const location = useLocation();
  const { itemData } = location.state || {};

  return (
    <div>
      <Navbar />
      {itemData ? <UpdateItemForm itemData={itemData} /> : <p>Loading...</p>}
    </div>
  );
};

export default UpdateItemPage;
