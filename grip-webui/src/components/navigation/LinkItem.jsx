import {Link, Route} from "react-router-dom";
import React from "react";

export default ({ components: { Item }, to, ...props }) => {
    return (
        <Route
            render={({ location: { pathname } }) => (
                <Item
                    component={({ children, className }) => (
                        <Link className={className} to={to}>
                            {children}
                        </Link>
                    )}
                    isSelected={pathname === to}
                    {...props}
                />
            )}
        />
    );
};
