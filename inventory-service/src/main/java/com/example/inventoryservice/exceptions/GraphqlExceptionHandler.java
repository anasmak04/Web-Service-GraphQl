package com.example.inventoryservice.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class GraphqlExceptionHandler extends DataFetcherExceptionResolverAdapter {


    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return new GraphQLError() {

            public String getMessage() {
                return ex.getMessage();
            }

            public List<SourceLocation> getLocations() {
                return null;
            }

            public ErrorClassification getErrorType() {
                return null;
            }
        };
    }

}

