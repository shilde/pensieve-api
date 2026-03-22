CREATE TABLE bookmarks
(
    id            UUID          NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    url           VARCHAR(2048) NOT NULL,
    title         VARCHAR(500)  NOT NULL,
    description   VARCHAR(1000),
    content       TEXT,
    embedding_id  UUID,
    collection_id UUID          REFERENCES collections (id) ON DELETE SET NULL,
    created_at    TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_bookmarks_collection_id ON bookmarks (collection_id);
CREATE INDEX idx_bookmarks_created_at ON bookmarks (created_at DESC);