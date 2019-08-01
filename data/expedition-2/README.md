

Checking validity: `xmlstarlet val *.html`

Listing all travis build identifiers: `jq .id *.json`

Listing all travis builds URL: `xmlstarlet sel -t -v '//node()[@id="travis-url"]' -n $x *.html`

Listing all repair tools: `xmlstarlet sel -t -v '//code/@title' -n $x *.html`

All builds fixed by Nopol: `xmlstarlet sel -t -v '//code[@title="Nopol"]/../..//a[@id="travis-url"]' -n $x *.html`

All builds fixed by NPEFix: `xmlstarlet sel -t -v '//code[@title="NPEFix"]/../..//a[@id="travis-url"]' -n $x *.html | sort -u`

From `data.7z` extracted on June 15 2019 and `patched_no_patches.7z` extracted on June 20 2019.
