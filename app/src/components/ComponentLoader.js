import React from "react";
import { TailSpin } from "react-loader-spinner";
function ComponentLoader() {
  return (
    <div className="col">
      <div
        className="card d-flex justify-content-center align-items-center text-center border-0"
        style={{
          width: "100%",
          height: "100%",
          backgroundColor: "transparent",
          boxShadow: "none",
        }}
      >
        <TailSpin
          height={40}
          width={40}
          color="#0d6efd"
          ariaLabel="tail-spin-loading"
          radius={1}
        />
      </div>
    </div>
  );
}
export default ComponentLoader;
