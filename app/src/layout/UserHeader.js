import React from "react";
import { Link, useNavigate } from "react-router-dom";

function UserHeader() {
  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">
            <h2>Bourbon Distillery Management</h2>
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link" aria-current="page" to="/user">
                  Home
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/user/addtask">
                  Add Task
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/user/pending-task">
                  Pending Task
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/user/completed-task">
                  Completed Task
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
}

export default UserHeader;
